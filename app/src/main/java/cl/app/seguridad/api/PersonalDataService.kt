package cl.app.seguridad.api
import cl.app.seguridad.data.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonalDataService {
    @GET("user/profile") // Reemplaza con la ruta real de tu API
    fun getPersonalData(): Call<Usuario>

    @GET("user/{id}") // Reemplaza con la ruta real de tu API
    fun getUser(@Path("id") id: Int): Call<Usuario>
}