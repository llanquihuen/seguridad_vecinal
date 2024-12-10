package cl.app.seguridad.api;

import cl.app.seguridad.data.Usuario
import cl.app.seguridad.data.dao.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body;
import retrofit2.http.GET
import retrofit2.http.POST;
import retrofit2.http.Query

interface LoginService {
    @POST("/api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): ResponseBody
}
interface ApiService {
    @GET("api/users/findrut")
    suspend fun findRut(@Query("rut") rut: String): ResponseBody
}

interface RegisterUserService {
    @POST("/api/auth/register")
    suspend fun register(@Body usuario: Usuario): Response<ResponseBody>
}
