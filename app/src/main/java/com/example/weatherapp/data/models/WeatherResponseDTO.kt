package com.example.weatherapp.data.models

import com.example.weatherapp.data.models.Forecast.WeatherDTO
import com.squareup.moshi.Json

data class WeatherResponseDTO(

    @Json(name="main")
    val mainDTO: MainDTO,

    @Json(name="weather")
    val weatherListDTO:List<WeatherDTO>,

    @Json(name="dt")
    val time:Long

)