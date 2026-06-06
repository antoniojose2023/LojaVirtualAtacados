package br.com.devandroid.lojavirtualatacados.presentation.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devandroid.lojavirtualatacados.R
import br.com.devandroid.lojavirtualatacados.databinding.ActivityDetalhesProdutoMainBinding
import br.com.devandroid.lojavirtualatacados.model.Product
import com.bumptech.glide.Glide

class DetalhesProdutoMainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetalhesProdutoMainBinding.inflate(layoutInflater) }

    private lateinit var produto: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras

        if(bundle != null){
            produto = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                bundle.getParcelable("produto", Product::class.java)!!

            }else{
                bundle.getParcelable("produto")!!

            }

            Log.i("TAG", "Detalhes do produto: ${produto.title} ")
            exibirDetalhesProduto(produto)
        }

        binding.btVoltar.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
        }


    }

    fun exibirDetalhesProduto(produto: Product){

         with(binding){
             Glide.with(this@DetalhesProdutoMainActivity).load(produto.thumbnail).into(ivDetalhesProduto)

             tvTituloDetalhesProduto.text = produto.title
             tvDescricaoDetalhesProduto.text = produto.description
             btCategoria.text = produto.category
             btPreco.text = "R$ ${produto.price}"
             btAvaliacao.text = produto.rating.toString()
             btEstoque.text = produto.stock.toString()
         }

    }
}