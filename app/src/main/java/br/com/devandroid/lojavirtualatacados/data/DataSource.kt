package br.com.devandroid.lojavirtualatacados.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataSource {

    fun getApiServiceDummyJson() = Retrofit
        .Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServiceDummyJson::class.java)
}