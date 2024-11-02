package cl.app.seguridad.pages.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.app.seguridad.api.RetrofitClient.personalDataService
import cl.app.seguridad.data.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showBackground = true)
@Composable
fun PersonalDataScreen(modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // Agrega isLoading aquí

    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = {
            isLoading = true
            personalDataService.getUser(1).enqueue(object :Callback<Usuario> { // Se usa userService aquí
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    isLoading = false
                    if (response.isSuccessful) {
                        val data = response.body()
                        nombre = data?.nombre ?: ""
                        direccion = data?.direccion ?: ""
                        email = data?.email ?: ""
                    } else {
                        // Manejar error de respuesta
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    isLoading = false
                    // Manejar error de solicitud
                }
            })
        }) {
            Text("Obtener Datos")
        }

        // ... resto del código ...
    }
}