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
        holder.comic = comics[position]
        holder.initializeViews()
    }
    inner class ViewHolder(private val binding: ItemListComicsBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var comic: ComicModel
        fun initializeViews() {
            Glide.with(binding.root.context)
                .load(comic.uriImage)
                .into(binding.imageViewItemComic)
        }
    }
}