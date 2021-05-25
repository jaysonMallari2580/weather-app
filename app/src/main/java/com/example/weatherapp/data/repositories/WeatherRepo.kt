package com.example.weatherapp.data.repositories

import com.example.weatherapp.data.remote.network.api.WeatherApiHelper
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val apiHelper: WeatherApiHelper) {

    suspend fun getWeather(cityName:String) = apiHelper.getWeather(cityName)

     suspend fun getForecast(lat:Double ,lon:Double ) = apiHelper.getForecast(lat,lon)

}