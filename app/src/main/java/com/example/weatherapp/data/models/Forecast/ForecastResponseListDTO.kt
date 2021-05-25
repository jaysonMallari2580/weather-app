package com.example.weatherapp.data.models.Forecast

import com.squareup.moshi.Json

data class ForecastResponseListDTO (

    @Json(name="hourly")
    val hourlyList : List<HourlyDTO>,

    @Json(name="daily")
    val dailyList : List<DailyDTO>

)