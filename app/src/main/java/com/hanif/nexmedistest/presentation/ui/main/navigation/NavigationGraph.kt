package com.hanif.nexmedistest.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hanif.nexmedistest.data.pref.PrefManager
import com.hanif.nexmedistest.presentation.ui.favorite.FavoriteScreen
import com.hanif.nexmedistest.presentation.ui.favorite.viewmodel.FavoriteViewModel
import com.hanif.nexmedistest.presentation.ui.main.SplashScreen
import com.hanif.nexmedistest.presentation.ui.main.viewmodel.MainViewModel
import com.hanif.nexmedistest.presentation.ui.movielist.MovieListScreen
import com.hanif.nexmedistest.presentation.ui.movielist.viewmodel.MovieListViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    mainVM: MainViewModel,
    movieListVM: MovieListViewModel,
    favoriteVM: FavoriteViewModel,
    prefManager: PrefManager,
    onBottomBarVisibilityChanged: (Boolean) -> Unit
){
    NavHost(
        navController = navController,
        startDestination = Routes.Welcome.routes)
    {
        composable(Routes.Welcome.routes){
            onBottomBarVisibilityChanged(false)
            SplashScreen(navController)
        }

        composable(BottomNavItems.Movie.route){
            onBottomBarVisibilityChanged(true)
            MovieListScreen(
                mainVM = mainVM,
                viewModel = movieListVM,
                prefManager = prefManager
            )
        }

        composable(BottomNavItems.Favorite.route){
            onBottomBarVisibilityChanged(true)
            FavoriteScreen(
                viewModel = favoriteVM,
                prefManager = prefManager
            )
        }
    }
}
