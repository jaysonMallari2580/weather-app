package com.example.weatherapp.data.remote.network.api

import com.example.weatherapp.data.models.Forecast.ForecastResponseListDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import com.example.weatherapp.data.remote.network.WeatherManager
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    companion object{
        private val include ="hourly,daily"
        private val appid = "600788429c6ebf4322a78f9bba4f58da"
        private val metric = "metric"
    }
    //api.openweathermap.org/data/2.5/weather?q=New%20Jersey&appid=600788429c6ebf4322a78f9bba4f58da
    @GET("data/2.5/forecast")
    suspend fun getWeather(@Query("q")q:String,
                           @Query("appid")appid:String= WeatherApiService.appid,
                           @Query("units")units:String= WeatherApiService.metric ) : WeatherResponseListDTO

    @GET("data/2.5/onecall")
    suspend fun getForecast(@Query("lat")lat:Double,
                            @Query("lon")lon:Double,
                            @Query("include")include:String = WeatherApiService.include,
                            @Query("appid")appid:String= WeatherApiService.appid) : ForecastResponseListDTO
}