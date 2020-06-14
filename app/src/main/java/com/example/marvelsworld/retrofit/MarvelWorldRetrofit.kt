package com.example.marvelsworld.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.marvelsworld.api.MarvelService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelWorldRetrofit {

    private val url = "https://gateway.marvel.com"
    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getInstance(): MarvelService = retrofit.create(MarvelService::class.java)

}