package com.jhostinluna.heroes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jhostinluna.heroes.R
import com.jhostinluna.heroes.core.common.Failure
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
        adapterRecyclerView = MyCharacterRecyclerViewAdapter()
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
                        adapterRecyclerView.notifyItemRangeInserted(0,uiState.data.size -1)
                        getBaseActivity().hideLoading()
                    }
                    is UIState.Loading -> {
                        getBaseActivity().showLoading()
                    }
                    is UIState.Error -> {
                        when(uiState.failure) {
                            is Failure.CustomError -> {
                                getBaseActivity().showMessageError(
                                    message = getString(R.string.stringCustomError)) { id ->
                                    Log.d("prueba", id.toString())
                                }
                            }
                            is Failure.ServerError -> {
                                getBaseActivity().showMessageError(
                                    message = getString(R.string.stringServerError)) { id ->
                                    Log.d("prueba", id.toString())
                                }
                            }
                            is Failure.NetworkConnectionError ->{
                                getBaseActivity().showMessageError(
                                    message = getString(R.string.stringNetworkConnectionError)) { id ->
                                    viewModel.loadCharacters()
                                }
                            }
                        }
                    }
                }
            }
        }
        adapterRecyclerView.onClickListener = {character, _ ->
            val bundle = Bundle()
            bundle.apply {
                putString("characterID",character.id.toString())
            }
            val navController = findNavController()
            navController.navigate("detail/${character.id}")
        }

    }

}