package com.example.climaapp.repository


import com.example.climaapp.model.WeatherModel
import com.example.climaapp.network.RetrofitInstance

class WeatherRepository {
    suspend fun getWeather(city: String): WeatherModel {
        return RetrofitInstance.api.getWeather(city)
    }
}
