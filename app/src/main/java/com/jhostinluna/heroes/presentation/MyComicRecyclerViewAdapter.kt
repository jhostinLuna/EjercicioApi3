package com.jhostinluna.heroes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhostinluna.heroes.databinding.ItemListComicsBinding
import com.jhostinluna.heroes.domain.models.ComicModel

class MyComicRecyclerViewAdapter(
): RecyclerView.Adapter<MyComicRecyclerViewAdapter.ViewHolder>() {
    var comics: List<ComicModel> = emptyList()
    internal val onClickListener: ( comic:ComicModel, view: View) -> Unit = {comic, view ->  }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListComicsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        Glide.with(holder.itemView.context)
            .load(comic.uriImage)
            .into(holder.imageViewComic)
        holder

        holder.imageViewComic.setOnClickListener {
            onClickListener(comic,it)
        }

    }
    inner class ViewHolder(binding: ItemListComicsBinding): RecyclerView.ViewHolder(binding.root) {
        val imageViewComic = binding.imageViewItemComic
    }
}