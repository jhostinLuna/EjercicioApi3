package com.jhostinluna.heroes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.jhostinluna.heroes.core.common.UIState
import com.jhostinluna.heroes.core.platform.BaseFragment
import com.jhostinluna.heroes.databinding.FragmentDetailCharacterBinding
import com.jhostinluna.heroes.domain.models.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "characterID"
@AndroidEntryPoint
class DetailCharacterFragment : BaseFragment() {
    private var _binding: FragmentDetailCharacterBinding? = null
    private val binding get() = _binding
    private val viewModel: ListCharacterFragmentViewModel by activityViewModels()
    private lateinit var adapter: MyComicRecyclerViewAdapter
    private var characterID:Int? = null
    private lateinit var characterModel: CharacterModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MyComicRecyclerViewAdapter()
        arguments?.let { bundle ->
            characterID = bundle.getInt(ARG_PARAM1)
            viewModel.loadComicsOfCharacter(characterID = characterID.toString())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCharacterBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUIStates()
        initializeViews()
    }
    private fun initializeViews(){
        binding?.apply {

            textViewTitleCharacterDetailF.text = characterModel.name
            textViewDescriptionCharacterDetailF.text = characterModel.description
            context?.let {c->
                Glide.with(c)
                    .load(characterModel.imageUrl)
                    .into(imageViewCharacterDetailF)
            }
        }


    }
    private fun initializeUIStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersState.collect {
                if (it is UIState.Success) {
                    characterModel = it.data.find { it.id == characterID }?: CharacterModel()
                }
            }
        }
        binding?.recyclerViewComics?.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.comicsOfCharacterUIState.collect {comicsUIState ->
                when(comicsUIState) {
                    is UIState.Success -> {
                        adapter.comics = comicsUIState.data
                        adapter.notifyDataSetChanged()

                    }
                    is UIState.Loading -> {

                    }
                    is UIState.Error -> {

                    }
                }

            }
        }
    }
    companion object{
        @JvmStatic
        fun newInstance(characterID: Int): DetailCharacterFragment =
            DetailCharacterFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1,characterID)
                }
            }
    }

}