package com.example.weatherapp.data.remote.network.api

import com.example.weatherapp.data.models.Forecast.ForecastResponseListDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO

interface WeatherApiHelper {
    suspend fun getWeather(q:String): WeatherResponseListDTO

    suspend fun getForecast(lat:Double, lon:Double) : ForecastResponseListDTO
}