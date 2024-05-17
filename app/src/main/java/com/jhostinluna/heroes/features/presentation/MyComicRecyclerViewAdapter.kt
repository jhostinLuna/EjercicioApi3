package com.jhostinluna.heroes.features.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhostinluna.heroes.databinding.ItemListComicsBinding

class MyComicRecyclerViewAdapter(
    private val listComic: List<String>
): RecyclerView.Adapter<MyComicRecyclerViewAdapter.ViewHolder>() {


    internal val onClickListener: ( comic:String, view: View) -> Unit = {comic, view ->  }


    inner class ViewHolder(binding: ItemListComicsBinding): RecyclerView.ViewHolder(binding.root) {
        val imageViewComic = binding.imageViewItemComic

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListComicsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listComic.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = listComic[position]
        Glide.with(holder.itemView.context)
            .load("")
            .into(holder.imageViewComic)

        holder.imageViewComic.setOnClickListener {
            onClickListener(comic,it)
        }

    }
}