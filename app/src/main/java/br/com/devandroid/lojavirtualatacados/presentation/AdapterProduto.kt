package br.com.devandroid.lojavirtualatacados.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.devandroid.lojavirtualatacados.databinding.ItemProdutoBinding
import br.com.devandroid.lojavirtualatacados.model.Product
import com.bumptech.glide.Glide

class AdapterProduto(val onClick: (Product) -> Unit = {}): RecyclerView.Adapter<AdapterProduto.ViewHolderProduto>() {
    private var listaProduto = mutableListOf<Product>()

    @SuppressLint("NotifyDataSetChanged")
    fun adicionarListaProduto(lista: List<Product>) {
        listaProduto.addAll( lista )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduto {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProdutoBinding.inflate(layoutInflater, parent, false)
            return ViewHolderProduto( binding )
    }

    override fun onBindViewHolder(holder: ViewHolderProduto, position: Int) {
           val produto = listaProduto[position]
           holder.bind( produto )
    }

    override fun getItemCount() = listaProduto.size

    inner class ViewHolderProduto(val binding: ItemProdutoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(produto: Product){
              binding.tvTtitulo.text = produto.title
              binding.tvDescricao.text = produto.description
              binding.tvPreco.text = "R$ ${produto.price}"
              Glide.with(itemView.context).load(produto.thumbnail).into(binding.ivProduto)

              binding.root.setOnClickListener {
                   onClick(produto)
              }
        }
    }

}