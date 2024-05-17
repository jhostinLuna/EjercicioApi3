package com.jhostinluna.heroes.features.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhostinluna.heroes.databinding.FragmentItemListBinding
import com.jhostinluna.heroes.fragments.placeholder.PlaceholderContent
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ListCharacterFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding
    private lateinit var adapterRecyclerView: MyCharacterRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {c->
            adapterRecyclerView = MyCharacterRecyclerViewAdapter(PlaceholderContent.ITEMS)
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
    }
}