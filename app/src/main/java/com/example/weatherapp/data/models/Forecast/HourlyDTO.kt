package com.example.weatherapp.data.models.Forecast

import com.squareup.moshi.Json

data class HourlyDTO(
    @Json(name="dt")
    val time : Long,

    @Json(name="temp")
    val temp : String,

    @Json(name="weather")
    val weatherList: List<WeatherDTO>
)