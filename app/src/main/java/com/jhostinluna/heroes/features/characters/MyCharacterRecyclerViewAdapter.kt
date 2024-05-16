package com.jhostinluna.heroes.features.characters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jhostinluna.heroes.fragments.placeholder.PlaceholderContent.PlaceholderItem
import com.jhostinluna.heroes.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a Characters.
 *
 */
class MyCharacterRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder>() {
    internal var onClickListener: (position: Int, view: View) -> Unit = {position, view ->  }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(holder.imageView.context)
            .load("https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onClickListener(position,it)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageViewItemCharacter

    }

}