package com.example.climaapp.network


import com.example.climaapp.BuildConfig
import com.example.climaapp.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherModel
}
