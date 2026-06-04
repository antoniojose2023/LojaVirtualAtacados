package br.com.devandroid.lojavirtualatacados.data

import br.com.devandroid.lojavirtualatacados.model.RespostaDummyJsonApi
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceDummyJson {

    @GET("products")
    suspend fun getRespostaApiDummyService(): Response<RespostaDummyJsonApi>

}