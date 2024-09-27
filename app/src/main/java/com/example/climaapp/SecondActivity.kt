package com.example.climaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.climaapp.databinding.ActivitySecondBinding
import com.example.climaapp.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso

class SecondActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Establecer título
        title = "Más detalles"

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // Botón de búsqueda para obtener el clima de la ciudad
        binding.buttonSearch.setOnClickListener {
            val city = binding.editTextCity.text.toString()
            viewModel.getWeather(city)
        }

        // Observar los datos del clima
        viewModel.weatherLiveData.observe(this, { weather ->
            val details = """
                Temp: ${weather.main.temp}°C
                Humidity: ${weather.main.humidity}%
                Pressure: ${weather.main.pressure} hPa
                Weather: ${weather.weather[0].description}
            """.trimIndent()

            binding.textViewWeatherDetails.text = details

            // Obtener y mostrar el icono del clima desde la API
            val iconCode = weather.weather[0].icon
            val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"
            Picasso.get().load(iconUrl).into(binding.imageViewWeatherIcon)
        })

        // Botón para regresar a la pantalla principal
        binding.buttonBack.setOnClickListener {
            finish()  // Cierra la actividad actual y regresa a la anterior
        }
    }
}

