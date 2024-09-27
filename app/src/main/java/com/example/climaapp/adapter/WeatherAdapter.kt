package com.example.climaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.climaapp.databinding.ItemWeatherBinding
import com.example.climaapp.model.WeatherModel

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherList = mutableListOf<WeatherModel>()

    // Función para agregar datos a la lista y actualizar el RecyclerView
    fun addWeather(weather: WeatherModel) {
        weatherList.add(weather)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size

    class WeatherViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherModel) {
            binding.textViewCity.text = weather.name
            binding.textViewTemp.text = "${weather.main.temp}°C"
        }
    }
}
