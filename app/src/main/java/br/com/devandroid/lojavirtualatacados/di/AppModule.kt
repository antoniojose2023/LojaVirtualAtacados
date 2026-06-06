package br.com.devandroid.lojavirtualatacados.di

import br.com.devandroid.lojavirtualatacados.data.ApiServiceDummyJson
import br.com.devandroid.lojavirtualatacados.repository.IRepositoryProdutos
import br.com.devandroid.lojavirtualatacados.repository.RepositoryProdutoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiServiceDummyJson(retrofit: Retrofit): ApiServiceDummyJson {
           return retrofit.create(ApiServiceDummyJson::class.java)

    }

    @Provides
    fun provideRepositoryProdutoImpl(apiServiceDummyJson: ApiServiceDummyJson): IRepositoryProdutos {
        return RepositoryProdutoImpl(apiServiceDummyJson)

    }

}