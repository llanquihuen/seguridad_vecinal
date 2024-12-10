package cl.app.seguridad.pages.login

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.app.seguridad.MainScreen
import cl.app.seguridad.pages.register.RegisterScreen


@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Login>{
            LoginScreen(
                {navController.navigate(Home)},
                {navController.navigate(RegisterQR)}
            )
        }
        composable<RegisterQR>{
            RegisterScreen(
                {navController.navigate(Login)},
            )
        }
        composable<Home>{
            MainScreen()
        }
    }
}