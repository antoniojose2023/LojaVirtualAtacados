package br.com.devandroid.lojavirtualatacados.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devandroid.lojavirtualatacados.presentation.AdapterProduto
import br.com.devandroid.lojavirtualatacados.presentation.view.DetalhesProdutoMainActivity
import br.com.devandroid.lojavirtualatacados.R
import br.com.devandroid.lojavirtualatacados.data.DataSource
import br.com.devandroid.lojavirtualatacados.databinding.ActivityMainBinding
import br.com.devandroid.lojavirtualatacados.presentation.viewmodel.ViewModelProduto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private val dataSource = DataSource
    private var adapterProduto = AdapterProduto()
    private val viewModelProduto: ViewModelProduto by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModelProduto.produtos.observe(this){ produtos ->
               if(produtos.isNotEmpty()){
                   adapterProduto.adicionarListaProduto(produtos)
                   configuureRecyclerViewProdutos()
               }
        }


        /* CoroutineScope(Dispatchers.IO).launch {
               val response = dataSource.getApiServiceDummyJson().getRespostaApiDummyService()
               if(response.isSuccessful && response.body() != null){
                   Log.i("TAG", "onCreate: ${response.message()}")
                   val listaProdutos = response.body()!!.products

                   withContext(Dispatchers.Main) {
                       adapterProduto.adicionarListaProduto(listaProdutos)
                       configuureRecyclerViewProdutos()
                   }

               }else{
                   Log.i("TAG", "onCreate: ${response.code()}")
               }
        }*/

        adapterProduto = AdapterProduto { produto ->
            val intent = Intent(this, DetalhesProdutoMainActivity::class.java)
            intent.putExtra("produto", produto)
            startActivity(intent)
        }

    }

    fun configuureRecyclerViewProdutos(){
          binding.rvProdutos.layoutManager = LinearLayoutManager(this)
          binding.rvProdutos.adapter = adapterProduto
    }

    override fun onStart() {
        super.onStart()
        viewModelProduto.getProdutos()
    }

}