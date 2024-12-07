package cl.app.seguridad.pages.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.CaptureActivity
import androidx.compose.ui.tooling.preview.Preview
import cl.app.seguridad.CaptureActivityPortait
import cl.app.seguridad.R


@Preview(showBackground = true)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RegisterScreen() {
    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA
        )
    )

    LaunchedEffect(key1 = Unit) {
        permissions.launchMultiplePermissionRequest()
    }

//    var listaStrings by remember { mutableStateOf(emptyList<String>()) }
    var dataCedula by remember { mutableStateOf("---") }
    var runCedula by remember { mutableStateOf<String?>(null) }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    val scanLauncher = rememberLauncherForActivityResult (
        contract = ScanContract(),
        onResult = {
            result -> dataCedula = result.contents ?: "---"
            runCedula = extractAndVerify(result.contents) ?: null
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.padding(0.dp, 30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Pagina de registro")
            }
            if (runCedula == null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Porfavor registre su cédula. " +
                                "Escanee la parte posterior de la cédula, donde se encuentra el código QR"
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.cedula),
                    contentDescription = "Logo",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .aspectRatio(1f)
                        .padding(0.dp, 30.dp),
                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.Center

                ) {
                    Button(
                        modifier = Modifier
                            .wrapContentSize(),
                        onClick = {
                            val scanOptions = ScanOptions()
                            scanOptions.setBeepEnabled(true)
                            scanOptions.setCaptureActivity(CaptureActivityPortait::class.java)
                            scanOptions.setOrientationLocked(false)
                            scanLauncher.launch(scanOptions)
                        }
                    ) {
                        Text(text = "Escanear QR")
                    }
                }
            }
            if (runCedula != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Registro de Usuario",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = apellido,
                        onValueChange = { apellido = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    runCedula?.let {
                        OutlinedTextField(
                            value = it,
                            onValueChange = { runCedula },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = passwordConfirmation,
                        onValueChange = { passwordConfirmation = it },
                        label = { Text("Confirmar Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = { Text("Dirección") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Registration Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Registrar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Cancelar")
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

fun extractAndVerify(text: String): String? {
    if (!text.contains("CEDULA")) {return null}

    // Regular expression to extract the desired text
    val regex = Regex("""RUN=(\d{6,8}-(?:\d|[kK]))""")
    val matchResult = regex.find(text)
    println(matchResult)
    // Return the extracted text if found, otherwise null
    return matchResult?.groupValues?.getOrNull(1)
}