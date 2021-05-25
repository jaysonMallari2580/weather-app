package com.example.weatherapp.data.remote.network.api

import com.example.weatherapp.data.models.Forecast.ForecastResponseListDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import javax.inject.Inject

class WeatherApiHelperImpl @Inject constructor(private val apiService: WeatherApiService): WeatherApiHelper{
    override suspend fun getWeather(cityName: String): WeatherResponseListDTO = apiService.getWeather(cityName)

    override suspend fun getForecast(lat: Double, lon: Double): ForecastResponseListDTO = apiService.getForecast(lat,lon)
}