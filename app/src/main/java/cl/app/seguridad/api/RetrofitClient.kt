package cl.app.seguridad.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8082/"

    private val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY) // Muestra elcuerpo de la solicitud y respuesta

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client) // Agrega el cliente con el logging interceptor
            .build()
    }

    val personalDataService: PersonalDataService by lazy {
        retrofit.create(PersonalDataService::class.java)
    }

    val authService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }
}