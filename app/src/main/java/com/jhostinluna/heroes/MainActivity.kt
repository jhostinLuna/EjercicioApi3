package com.jhostinluna.heroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.jhostinluna.heroes.databinding.ActivityMainBinding
import com.jhostinluna.heroes.presentation.ListCharacterFragment
import com.jhostinluna.heroes.presentation.ListCharacterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    val viewmodel: ListCharacterFragmentViewModel by viewModels()
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState == null) {
            val fTransaction = supportFragmentManager.beginTransaction()
            val listCharacterFragment = ListCharacterFragment()
            fTransaction.add(R.id.fragment_container_view_id,listCharacterFragment)
            fTransaction.commit()
        }

        setSupportActionBar(binding.toolbar)


    }
    fun loadFragment(fragment: Fragment, oldFragmentClassName: String){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view_id,fragment)
        fragmentTransaction.addToBackStack(oldFragmentClassName)
        fragmentTransaction.commit()
    }
}