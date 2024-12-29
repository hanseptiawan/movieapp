package com.hanif.nexmedistest.presentation.ui.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    object Movie : BottomNavItems(
        title = "Movie",
        icon = Icons.Outlined.Home,
        route = "movie_list"
    )
    object Favorite : BottomNavItems(
        title = "Favorite",
        icon = Icons.Outlined.Favorite,
        route = "favorite_list"
    )

}