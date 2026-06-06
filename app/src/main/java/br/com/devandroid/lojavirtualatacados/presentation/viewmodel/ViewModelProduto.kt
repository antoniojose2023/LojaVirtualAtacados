package br.com.devandroid.lojavirtualatacados.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devandroid.lojavirtualatacados.model.Product
import br.com.devandroid.lojavirtualatacados.repository.IRepositoryProdutos
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelProduto @Inject constructor(private val repository: IRepositoryProdutos): ViewModel() {
    private var _produtos = MutableLiveData<List<Product>>()
    val produtos: LiveData<List<Product>> = _produtos


    fun getProdutos(){
          viewModelScope.launch {
                 val listaProdutos = repository.getProdutos()
                 _produtos.value = listaProdutos
          }
    }
}