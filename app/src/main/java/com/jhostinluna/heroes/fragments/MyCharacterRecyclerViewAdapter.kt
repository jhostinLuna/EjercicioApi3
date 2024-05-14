package com.jhostinluna.heroes.fragments

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.jhostinluna.heroes.fragments.placeholder.PlaceholderContent.PlaceholderItem
import com.jhostinluna.heroes.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCharacterRecyclerViewAdapter(
    private val context: Context,
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun itemClick(viewId: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val item = values[position]
        Glide.with(context)
            .load("https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageView

        init {
            binding.root.setOnClickListener {view->
                onItemClickListener.itemClick(view.id)
            }
        }
    }

}