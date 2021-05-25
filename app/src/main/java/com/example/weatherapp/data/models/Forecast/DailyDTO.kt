package com.example.weatherapp.data.models.Forecast

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class DailyDTO (

    @Json(name="dt")
    val time: Long,

    @Json(name="temp")
    val temp: TempDailyDTO,

    @Json(name="weather")
    val weatherList : List<WeatherDailyDTO>
)