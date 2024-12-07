package cl.app.seguridad.pages.login

import cl.app.seguridad.pages.login.LoginState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cl.app.seguridad.activity.SessionManager

@Composable
fun LoginRoute(
    viewModel: LoginViewModel,
    sessionManager: SessionManager,
    onLoginSuccess: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()

    // Efecto para manejar el éxito del login
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            // Guardar token
           // sessionManager.saveToken((loginState as LoginState.Success).data.token)
            // Navegar a la siguiente pantalla
            onLoginSuccess()
        }
    }

//    LoginScreen(
//        onLogin = { username, password ->
//            viewModel.login(username, password)
//        },
//        loading = loginState is LoginState.Loading,
//        error = if (loginState is LoginState.Error) {
//            (loginState as LoginState.Error).message
//        } else null
//    )
}