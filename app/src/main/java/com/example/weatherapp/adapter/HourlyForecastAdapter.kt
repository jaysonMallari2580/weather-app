package com.example.weatherapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Forecast.HourlyDTO
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import com.example.weatherapp.databinding.HourlyWeatherItemBinding
import java.text.SimpleDateFormat
import java.util.*

class HourlyForecastAdapter(val mHourlyList: List<HourlyDTO>) :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        return HourlyForecastViewHolder(
            HourlyWeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        val hourlyForecast = mHourlyList[position]
        holder.bindData(hourlyForecast,position)

    }

    override fun getItemCount(): Int {
        return mHourlyList.size
    }

    override fun onViewRecycled(holder: HourlyForecastViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.binding.hourIconIv)
    }

    class HourlyForecastViewHolder(val binding: HourlyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(hourlyForecast: HourlyDTO, position: Int) {
            // label
            val date = Date(hourlyForecast.time * 1000)
            val simpleDateFormat = SimpleDateFormat("HH",Locale.getDefault())
            val time = simpleDateFormat.format(date)
            if(position == 0){
                binding.hourNameTv.text = "Now"
            }else {
                binding.hourNameTv.text = getTime(time)

            }

            //temp
            val temp = calculateFahrenheit(hourlyForecast.temp!!.toDouble()).toInt().toString()+"\u00B0"
            binding.hourlyTemp.text = temp

            //icon
            var iconCode = hourlyForecast.weatherList[0].icon
            var iconUrl = "https://openweathermap.org/img/w/$iconCode.png";
            Glide.with(binding.hourIconIv.context)
                .load(iconUrl)
                .placeholder(R.drawable.ic_baseline_cloud_queue_24)
                .into(binding.hourIconIv);
        }

        private fun calculateFahrenheit(degrees: Double): Double {
            val degreesInFahrenheit = ((degrees * 9 / 5) - 459.67)
            return degreesInFahrenheit
        }

        private fun getTime(hour: String):String{
            var time = hour.toInt()

            var amOrPM = if(time<12) "AM" else "PM"

            if(time == 0){
                time = 12
            }else if(time >12) {
                time -= 12
            }

            return time.toString() + amOrPM
        }

    }
}