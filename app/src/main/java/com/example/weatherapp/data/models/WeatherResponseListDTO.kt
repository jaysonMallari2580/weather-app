package com.example.weatherapp.data.models

import com.squareup.moshi.Json

data class WeatherResponseListDTO (

    @Json(name="list")
    val list: List<WeatherResponseDTO>,

    @Json(name="city")
    val city: CityDTO?
    )