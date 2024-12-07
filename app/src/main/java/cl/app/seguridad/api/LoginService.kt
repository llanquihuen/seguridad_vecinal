package cl.app.seguridad.api;

import retrofit2.Response
import retrofit2.http.Body;
import retrofit2.http.POST;

interface LoginService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

data class LoginRequest(
        val username: String,
        val password: String
)

data class LoginResponse(
    val token: String
)