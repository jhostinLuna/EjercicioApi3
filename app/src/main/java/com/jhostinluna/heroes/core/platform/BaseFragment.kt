package com.jhostinluna.heroes.core.platform

import androidx.fragment.app.Fragment
import com.jhostinluna.heroes.presentation.navigation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment: Fragment() {
    open fun onBackPressed() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
    fun getBaseActivity(): MainActivity = activity as MainActivity
}