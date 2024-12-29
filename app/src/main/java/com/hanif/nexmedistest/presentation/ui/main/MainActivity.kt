package com.hanif.nexmedistest.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hanif.nexmedistest.data.pref.PrefManager
import com.hanif.nexmedistest.presentation.theme.NexmedisTestTheme
import com.hanif.nexmedistest.presentation.ui.favorite.viewmodel.FavoriteViewModel
import com.hanif.nexmedistest.presentation.ui.main.components.BottomBar
import com.hanif.nexmedistest.presentation.ui.main.navigation.NavigationGraph
import com.hanif.nexmedistest.presentation.ui.main.viewmodel.MainViewModel
import com.hanif.nexmedistest.presentation.ui.movielist.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var prefManager: PrefManager
    private val mainVM: MainViewModel by viewModels()
    private val movieListVM: MovieListViewModel by viewModels()
    private val favoriteVM: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexmedisTestTheme {
                val navController: NavHostController = rememberNavController()
                var buttonsVisible by remember { mutableStateOf(true) }

                Scaffold(
                    bottomBar = {
                        if (buttonsVisible) {
                            BottomBar(
                                navController = navController,
                                state = buttonsVisible,
                                modifier = Modifier
                            )
                        }
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        NavigationGraph(
                            navController = navController,
                            mainVM = mainVM,
                            movieListVM = movieListVM,
                            favoriteVM = favoriteVM,
                            prefManager = prefManager,
                        ) {
                                isVisible ->
                            buttonsVisible = isVisible
                        }
                    }
                }
            }
        }
    }
}