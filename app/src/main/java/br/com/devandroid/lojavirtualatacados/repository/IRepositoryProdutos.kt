package br.com.devandroid.lojavirtualatacados.repository

import br.com.devandroid.lojavirtualatacados.model.Product

interface IRepositoryProdutos {
    suspend fun getProdutos(): List<Product>
}