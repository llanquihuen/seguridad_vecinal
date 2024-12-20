package cl.app.seguridad.pages.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun PersonalData (modifier: Modifier = Modifier) {

    var nombre by remember {mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    //var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    //Realiza la llamada a la API cuando se inicia la función composable
    LaunchedEffect(key1 = Unit) {
        personalDataService.getUser(1).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    nombre = data?.nombre ?: ""
                    direccion = data?.direccion ?: ""
                    //telefono = data?.telefono ?: ""
                    email = data?.email ?: ""
                    // ... actualiza otros campos
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                // Maneja el error
            }
        })
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { },
            label = { Text("Nombre") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "Av. Siempreviva 742",
            onValueChange = { },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )
//        Spacer(modifier = Modifier.height(8.dp))
//        OutlinedTextField(
//            value = "+569 1234 5678",
//            onValueChange = {  },
//            label = { Text("Teléfono") },
//            readOnly = true,
//            modifier = Modifier.fillMaxWidth()
//        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "cindyberrios02@gmail.com",
            onValueChange = {},
            label = { Text("Email") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "cindyberrios02@gmail.com",
            onValueChange = {},
            label = { Text("Email") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        // ... otros campos
    }
}