package com.example.weatherapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.Forecast.ForecastResponseListDTO
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import com.example.weatherapp.data.remote.network.WeatherManager
import com.example.weatherapp.data.remote.network.api.WeatherApiHelper
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val apiHelper: WeatherApiHelper) {

    suspend fun getWeather(cityName:String) = apiHelper.getWeather(cityName)

     suspend fun getForecast(lat:Double ,lon:Double ) = apiHelper.getForecast(lat,lon)

}