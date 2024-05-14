package com.jhostinluna.heroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jhostinluna.heroes.databinding.FragmentDetailCharacterBinding

class DetailCharacterFragment : Fragment() {
    private var _binding: FragmentDetailCharacterBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCharacterBinding.inflate(inflater,container,false)
        return binding?.root
    }
}