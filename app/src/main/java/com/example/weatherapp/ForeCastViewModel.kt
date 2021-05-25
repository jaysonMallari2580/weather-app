package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.Forecast.DailyDTO
import com.example.weatherapp.data.models.Forecast.HourlyDTO
import com.example.weatherapp.data.repositories.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(private val weatherRepo: WeatherRepo): ViewModel() {

    private var _dailyForecastList = MutableLiveData<List<DailyDTO>>()
    val dailyForecastList get() = _dailyForecastList

    private var _hourlyForecastList = MutableLiveData<List<HourlyDTO>>()
    val hourlyForecastList get() = _hourlyForecastList



    fun getForecast(lat:Double,lon:Double) = viewModelScope.launch(Dispatchers.IO) {
        try{
            val dailyListDeferred = async{weatherRepo.getForecast(lat, lon).dailyList}
            val hourListDeferred = async{weatherRepo.getForecast(lat, lon).hourlyList}
            _dailyForecastList.postValue(dailyListDeferred.await())
            _hourlyForecastList.postValue(hourListDeferred.await())
        }catch (e:Exception){
            Log.d("FORECAST response NOT working", e.message.toString())
        }
    }


}