package com.jhostinluna.heroes.presentation

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jhostinluna.heroes.databinding.FragmentItemBinding
import com.jhostinluna.heroes.domain.models.CharacterModel

/**
 * [RecyclerView.Adapter] that can display a Characters.
 *
 */
class MyCharacterRecyclerViewAdapter(

) : RecyclerView.Adapter<MyCharacterRecyclerViewAdapter.ViewHolder>() {
    internal var onClickListener: (character: CharacterModel, view: View) -> Unit = { characterModel, view ->
        Log.d("prueba", characterModel.name)
    }
    var characters: List<CharacterModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.characterModel = characters[position]
        holder.initializeViews()
        holder.initListener(onClickListener)
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var characterModel: CharacterModel

        fun initializeViews() {
            Glide.with(binding.root.context)
                .load(characterModel.imageUrl)
                .into(binding.imageViewItemCharacter)
            binding.textViewTitleItemCharacter.text = characterModel.name
            binding.textViewDescriptionCharacter.text = characterModel.description

        }
        fun initListener(clickListener: (character: CharacterModel, view: View) -> Unit){
            binding.textViewTitleItemCharacter.setOnClickListener {
                clickListener(characterModel,it)
            }
            binding.imageViewItemCharacter.setOnClickListener{
                clickListener(characterModel,it)
            }
        }
    }

}