package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.DailyForecastAdapter
import com.example.weatherapp.adapter.HourlyForecastAdapter
import com.example.weatherapp.databinding.MainFragmentBinding
import java.time.LocalDate

class MainFragment : Fragment() {

    lateinit var binding: MainFragmentBinding
    lateinit var cityName:String

    companion object {
        fun newInstance() = MainFragment()

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

    }

    private lateinit var viewModel: MainViewModel
    private lateinit var fViewModel: ForeCastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        fViewModel = ViewModelProvider(this).get(ForeCastViewModel::class.java)

        //hourly
         fViewModel.hourlyForecastList.observe(viewLifecycleOwner, Observer {

             binding.hourlyWeatherRecyclerview.apply {
                 layoutManager =
                     LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                 adapter =HourlyForecastAdapter(it)


             }
         })

        //daily
        fViewModel.dailyForecastList.observe(viewLifecycleOwner, Observer {

            binding.dailyWeatherRecyclerview.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter =DailyForecastAdapter(it)
            }
        })



        //temp
        viewModel.mainInfo.observe(viewLifecycleOwner, Observer {
            binding.tempTextview.text = it.toString()
        })

        viewModel.mainInfoMaxTemp.observe(viewLifecycleOwner, Observer {
            binding.currentWeatherHighTv.text = it.toString()
        })

        viewModel.mainInfoMaxTemp.observe(viewLifecycleOwner, Observer {
            binding.currentWeatherLowTv.text = it.toString()
        })

        //cityname
        viewModel.cityNameInfo.observe(viewLifecycleOwner, Observer {
            binding.loactionTextview.text = it.toString()
        })

        //weather hint
        viewModel.weatherHint.observe(viewLifecycleOwner, Observer {
            binding.weatherTextview.text = it.toString()
        })



        //Search
        binding.searchIcon.setOnClickListener(View.OnClickListener {

            cityName = binding.searchEdittext.editText?.text.toString()

            val toast =
                Toast.makeText(context, "You have entered $cityName. . .", Toast.LENGTH_LONG)
            toast.show()

            it.hideKeyboard()
            binding.searchEdittext.editText?.setText("")

            //Day
            var day: String = LocalDate.now().dayOfWeek.name
            binding.todaysNameTextview.setText(day)

            //observe lat and lon
            viewModel.getWeather(cityName)
            viewModel.weatherResponse.observe(viewLifecycleOwner, Observer {
                viewModel.onGetWeatherSuccess(it)
                fViewModel.getForecast(viewModel.lat,viewModel.lon)
            })
        })
    }


}