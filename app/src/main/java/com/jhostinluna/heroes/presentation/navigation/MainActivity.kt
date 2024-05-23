package com.jhostinluna.heroes.presentation.navigation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.jhostinluna.heroes.R
import com.jhostinluna.heroes.databinding.ActivityMainBinding
import com.jhostinluna.heroes.presentation.DetailCharacterFragment
import com.jhostinluna.heroes.presentation.ListCharacterFragment
import com.jhostinluna.heroes.presentation.ListCharacterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    val viewmodel: ListCharacterFragmentViewModel by viewModels()
    private lateinit var navController: NavController
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


        setSupportActionBar(binding.toolbar)
        initNavController()

    }

    fun showLoading() {
        if (binding.progressBar.visibility == View.GONE)
            binding.progressBar.visibility = View.VISIBLE
    }
    fun hideLoading() {
        if (binding.progressBar.visibility == View.VISIBLE)
            binding.progressBar.visibility = View.GONE
    }
    private fun initNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view_id) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = "home"
        ) {
            fragment<ListCharacterFragment>("home") {
                label = "List of Characters"
            }
            fragment<DetailCharacterFragment>("detail/{characterID}") {
                label = "Detail of Characters"
                argument("characterID") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}