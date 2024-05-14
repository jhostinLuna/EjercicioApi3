package com.jhostinluna.heroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jhostinluna.heroes.databinding.ActivityMainBinding
import com.jhostinluna.heroes.fragments.ListCharacterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
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
            val listCharacterFragment = ListCharacterFragment.newInstance(3)
            fTransaction.add(R.id.fragment_container_view_id,listCharacterFragment)
            fTransaction.commit()
        }

        setSupportActionBar(binding.toolbar)


    }
}