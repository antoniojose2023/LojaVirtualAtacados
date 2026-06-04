package br.com.devandroid.lojavirtualatacados.model

data class RespostaDummyJsonApi(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)