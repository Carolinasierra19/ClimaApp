package com.example.climaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.climaapp.adapter.WeatherAdapter
import com.example.climaapp.databinding.ActivityMainBinding
import com.example.climaapp.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Configurar el Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setContentInsetsAbsolute(0, 0)

        // Configurar el RecyclerView
        adapter = WeatherAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // Hardcodear las ciudades para mostrar su clima
        val cities = listOf("Buenos Aires", "Paris", "Tokyo", "New York", "Sydney",
            "Madrid", "Berlin", "Toronto", "Rome", "Cairo",
            "Moscow", "Beijing", "Mexico City", "Santiago", "Cape Town")
        cities.forEach { city ->
            viewModel.getWeather(city)
        }

        // Observar los datos del clima y actualizar el RecyclerView
        viewModel.weatherLiveData.observe(this, { weather ->
            adapter.addWeather(weather)
        })

        // Manejar errores si ocurren
        viewModel.errorLiveData.observe(this, { error ->
            // Muestra el error en un Toast o alguna notificación
        })

        // Configurar el botón para navegar a otra actividad
        binding.buttonMore.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}



