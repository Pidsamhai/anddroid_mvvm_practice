package com.menggxyz.forcastmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.menggxyz.forcastmvvm.data.response.CurrentWeatherResponse
import com.menggxyz.forcastmvvm.data.response.Game
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "21da6f9924a3b5598bde672ccdf00066"

const val GAME_URL= "https://api.crackwatch.com/api/"

//https://api.crackwatch.com/api/games

//http://api.weatherstack.com/current?access_key=21da6f9924a3b5598bde672ccdf00066&query=Thai%20Land

interface ApixuWeatherApiServices {
    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String
        //@Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    @GET("games")
    fun getGame(
        @Query("page") page: String
    ):Deferred<Game>

    companion object {
        operator fun invoke(): ApixuWeatherApiServices {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GAME_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiServices::class.java)
        }

    }
}