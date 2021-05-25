package com.example.weatherapp.data.models.Forecast

import com.squareup.moshi.Json

data class TempDailyDTO (

    @Json(name="day")
    val dayTemp : Double,

    @Json(name="min")
    val minTempDaily : Double,

    @Json(name="max")
    val maxTempDaily : Double

)