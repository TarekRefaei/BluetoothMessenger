package com.tarekrefaei.bluetoothmessenger

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tarekrefaei.bluetoothmessenger.features.chat.screen.BluetoothLeScanner
import com.tarekrefaei.bluetoothmessenger.features.splash.screen.SplashScreen

sealed class Screens(val route: String) {
    object SplashScreen : Screens(route = "Splash_Screen")
    object ScanningScreen : Screens(route = "Scanning_Screen")
}

@Composable
fun Destinations(
    navController: NavHostController,
    permissionLauncher: ActivityResultLauncher<Array<String>>,
    context: Context,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(
                navController = navController,
            )
        }
        composable(route = Screens.ScanningScreen.route) {
            BluetoothLeScanner(
                permissionLauncher = permissionLauncher,
                context = context,
            )
        }
    }
}