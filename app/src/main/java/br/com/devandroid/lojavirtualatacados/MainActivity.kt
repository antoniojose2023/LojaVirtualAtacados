package br.com.devandroid.lojavirtualatacados

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devandroid.lojavirtualatacados.data.DataSource
import br.com.devandroid.lojavirtualatacados.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private val dataSource = DataSource
    private var adapterProduto = AdapterProduto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        CoroutineScope(Dispatchers.IO).launch {
               val response = dataSource.getApiServiceDummyJson().getRespostaApiDummyService()
               if(response.isSuccessful && response.body() != null){
                   Log.i("TAG", "onCreate: ${response.message()}")
                   val listaProdutos = response.body()!!.products

                   withContext(Dispatchers.Main){
                       adapterProduto.adicionarListaProduto(listaProdutos)
                       configuureRecyclerViewProdutos()
                   }

               }else{
                   Log.i("TAG", "onCreate: ${response.code()}")
               }
        }


    }

    fun configuureRecyclerViewProdutos(){
          binding.rvProdutos.layoutManager = LinearLayoutManager(this)
          binding.rvProdutos.adapter = adapterProduto
    }

}