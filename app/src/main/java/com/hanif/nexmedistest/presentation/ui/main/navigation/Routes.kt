package com.hanif.nexmedistest.presentation.ui.main.navigation

sealed class Routes(val routes: String) {

    object Welcome : Routes("splash_screen")

}