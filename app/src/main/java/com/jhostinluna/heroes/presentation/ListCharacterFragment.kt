package com.jhostinluna.heroes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.jhostinluna.heroes.core.common.UIState
import com.jhostinluna.heroes.core.platform.BaseFragment
import com.jhostinluna.heroes.databinding.FragmentItemListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ListCharacterFragment : BaseFragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding
    private lateinit var adapterRecyclerView: MyCharacterRecyclerViewAdapter
    private val viewModel: ListCharacterFragmentViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {c->
            adapterRecyclerView = MyCharacterRecyclerViewAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.root?.adapter = adapterRecyclerView
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersState.collect{uiState->
                when(uiState) {
                    is UIState.Success -> {
                        adapterRecyclerView.characters = uiState.data
                        adapterRecyclerView.notifyDataSetChanged()

                    }
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                }
            }
        }
        adapterRecyclerView.onClickListener = {character, view ->
            val fragment = DetailCharacterFragment.newInstance(characterID = character.id)
            getBaseActivity().loadFragment(fragment, this::class.java.simpleName)
        }

    }
}