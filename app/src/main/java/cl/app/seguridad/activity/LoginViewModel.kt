package cl.app.seguridad.pages.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.app.seguridad.api.LoginRequest
import cl.app.seguridad.api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = RetrofitClient.authService.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        // Aquí puedes guardar el token en algún tipo de almacenamiento seguro
                        // Por ejemplo, usando DataStore o SharedPreferences
                        _loginState.value = LoginState.Success(token)
                    } else {
                        _loginState.value = LoginState.Error("Token inválido")
                    }
                } else {
                    _loginState.value = LoginState.Error(response.message() ?: "Error de inicio de sesión")
                }
            } catch (e: HttpException) {
                _loginState.value = LoginState.Error("Error de autenticación: ${e.code()}")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error de red: ${e.message}")
            }
        }
    }
}

sealed class LoginState {
    object Initial : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}