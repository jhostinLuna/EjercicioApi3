package com.jhostinluna.heroes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jhostinluna.heroes.core.common.UIState
import com.jhostinluna.heroes.databinding.FragmentItemListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ListCharacterFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding
    private lateinit var adapterRecyclerView: MyCharacterRecyclerViewAdapter
    private val viewModel: ListCharacterFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {c->
            adapterRecyclerView = MyCharacterRecyclerViewAdapter(emptyList())
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
                        adapterRecyclerView = MyCharacterRecyclerViewAdapter(uiState.data)
                        binding?.root?.adapter = adapterRecyclerView
                        adapterRecyclerView.notifyDataSetChanged()
                    }
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                }
            }
        }
    }
}