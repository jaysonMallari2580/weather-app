package com.example.weatherapp.data.models

import com.squareup.moshi.Json

data class MainDTO(
    @Json(name="temp")
    val temp: String?,
    @Json(name="feels_like")
    val feels_like: String?,
    @Json(name="temp_min")
    val temp_min: String?,
    @Json(name="temp_max")
    val temp_max: String?,
    @Json(name="pressure")
    val pressure: String?,
    @Json(name="sea_level")
    val sea_level: String?,
    @Json(name="grnd_level")
    val grnd_level: String?,
    @Json(name="humidity")
    val humidity: String?,
    @Json(name="temp_kf")
    val temp_kf: String?
)