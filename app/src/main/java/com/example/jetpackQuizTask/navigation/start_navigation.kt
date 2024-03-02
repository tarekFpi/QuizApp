package com.example.jetpackQuizTask.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackQuizTask.screen.HomeScreen
import com.example.jetpackQuizTask.screen.StartScreen

@Composable
fun StarBucksNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = start){
        composable(start){
            StartScreen(navHostController = navHostController)
        }
        composable(home){
           HomeScreen(navHostController = navHostController)
        }

    }
}

const val start = "start_screen"
const val home = "home_screen"

