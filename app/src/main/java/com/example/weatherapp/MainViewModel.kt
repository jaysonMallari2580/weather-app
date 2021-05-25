package com.example.weatherapp
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import com.example.weatherapp.data.repositories.WeatherRepo
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherRepo: WeatherRepo):ViewModel(){

    //lat &lon
    private var _lat:Double =0.00
    val lat get() = _lat

    private var _lon:Double = 0.00
    val lon get() = _lon

    private var _weatherList = MutableLiveData<List<WeatherResponseDTO>>()
    val weatherList get() = _weatherList

    private var _weatherResponse = MutableLiveData<WeatherResponseListDTO>()
    val weatherResponse get() = _weatherResponse

    var _cityName = String()
    val cityName get() = _cityName



    private var _test = MutableLiveData<String>()
    val test get() = _test

    private var _mainInfoMinTemp = MutableLiveData<String>()
    val mainInfoMinTemp = _mainInfoMinTemp

    private var _mainInfoMaxTemp = MutableLiveData<String>()
    val mainInfoMaxTemp = _mainInfoMaxTemp

    private var _mainInfo = MutableLiveData<String>()
    val mainInfo get() = _mainInfo

    private var _cityNameInfo = MutableLiveData<String>()
    val cityNameInfo get() = _cityNameInfo

    private var _weatherHint = MutableLiveData<String>()
    val weatherHint get() = _weatherHint

    private var _icon = MutableLiveData<String>()
    val icon get() = _icon



    fun getWeather(cityName: String) = viewModelScope.launch(Dispatchers.IO) {
        try{
            val weatherListDeferred = async{weatherRepo.getWeather(cityName)}
             _weatherResponse.postValue(weatherListDeferred.await())
        }catch (e: Exception){
            Log.d("Weather LIST is NOT working", e.message.toString())
        }
    }


    fun onGetWeatherSuccess(weatherResponse: WeatherResponseListDTO) {

        _lat = weatherResponse.city?.coord?.lat!!
        _lon = weatherResponse.city?.coord?.lon!!

        println("_LAT = $lat and _LON = $lon")

        _weatherList.value = weatherResponse.list

        _test.value = weatherResponse.list[0].mainDTO.temp

        //temp
        _mainInfo.value = calculateFahrenheit(weatherResponse.list[0].mainDTO.temp!!.toDouble()).toInt().toString()+"\u00B0"

        _mainInfoMaxTemp.value = calculateFahrenheit(weatherResponse.list[0].mainDTO.temp_max!!.toDouble()).toInt().toString()+"\u00B0"

        _mainInfoMinTemp.value = calculateFahrenheit(weatherResponse.list[0].mainDTO.temp_min!!.toDouble()).toInt().toString()+"\u00B0"
        //cityName
        _cityNameInfo.value = weatherResponse.city?.name

        //weather Hint
        _weatherHint.value = weatherResponse.list[0].weatherListDTO[0].description.toString()

        //weather Icon
        _icon.value = weatherResponse.list[0].weatherListDTO[0].icon.toString()
    }

    private fun calculateFahrenheit(degrees: Double): Double {
        val degreesInFahrenheit = (degrees * 1.8) + 32
        return degreesInFahrenheit
    }



}


