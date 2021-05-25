package com.example.weatherapp.di

import com.example.weatherapp.data.remote.network.api.WeatherApiHelper
import com.example.weatherapp.data.remote.network.api.WeatherApiHelperImpl
import com.example.weatherapp.data.remote.network.api.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    companion object{
        private const val TIMEOUT = 60 * 1000.toLong()
        private const val BASE_URL = "https://api.openweathermap.org/"

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)

        val clientBuilder =
            OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(
                TIMEOUT,
                TimeUnit.SECONDS
            ).writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitService(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(providesOkHttpClient())
            .build()

    //service = retrofit.create(WeatherApiService::class.java)
    @Provides
    @Singleton
    fun providesWeatherApiService(retrofit: Retrofit) = retrofit.create(WeatherApiService::class.java)


    @Provides
    @Singleton
    fun providesWeatherApiHelper(apiHelper: WeatherApiHelperImpl) :WeatherApiHelper = apiHelper
}