package com.example.weatherapp.data.models.Forecast


import com.squareup.moshi.Json

data class WeatherDTO(

    @Json(name="id")
    val id: String?,

    @Json(name="main")
    val main: String?,

    @Json(name="description")
    val description: String?,

    @Json(name="icon")
    val icon: String?,
    )