package com.jhostinluna.heroes.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jhostinluna.heroes.databinding.FragmentItemBinding
import com.jhostinluna.heroes.domain.entities.CharacterModel

/**
 * [RecyclerView.Adapter] that can display a Characters.
 *
 */
class MyCharacterRecyclerViewAdapter(
    private val values: List<CharacterModel>
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
            .load(item.imageUrl.replace("http","https"))
            .into(holder.imageView)
        holder.title.text = item.name
        holder.description.text = item.description
        holder.itemView.setOnClickListener {
            onClickListener(position,it)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageViewItemCharacter
        val title = binding.textViewTitleItemCharacter
        val description = binding.textViewDescriptionCharacter
    }

}