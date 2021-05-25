package com.example.weatherapp.data.models.Forecast

import com.squareup.moshi.Json

data class WeatherDailyDTO (

    @Json(name="icon")
    val icon:String
)