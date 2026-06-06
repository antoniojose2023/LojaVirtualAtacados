package br.com.devandroid.lojavirtualatacados.repository

import br.com.devandroid.lojavirtualatacados.data.ApiServiceDummyJson
import br.com.devandroid.lojavirtualatacados.model.Product
import javax.inject.Inject

class RepositoryProdutoImpl @Inject constructor(private val apiService: ApiServiceDummyJson): IRepositoryProdutos {
    override suspend fun getProdutos(): List<Product> {
           val response = apiService.getRespostaApiDummyService()

           try{
               if(response.isSuccessful && response.body() != null){
                     val produtos = response.body()!!.products
                     return produtos
               }
           }catch (e: Exception){
              e.printStackTrace()
           }

           return emptyList()
    }

}