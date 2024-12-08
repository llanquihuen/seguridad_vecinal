package cl.app.seguridad.pages.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondoverde), // Reemplaza "fondo_registro" con el nombre de tu imagen
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(1f), // Puedes ajustar la opacidad entre 0f y 1f
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(0.dp, 30.dp))
            if(runCedula ==null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Página de registro",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFFffffff),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        textAlign = TextAlign.Center
                    )
                }
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
                                "Escanee la parte posterior de la cédula, donde se encuentra el código QR",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFFffffff),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.cedula),
                    contentDescription = "Logo",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(231, 107, 3)

                        ),
                        onClick = {
                            val scanOptions = ScanOptions()
                            scanOptions.setBeepEnabled(true)
                            scanOptions.setCaptureActivity(CaptureActivityPortait::class.java)
                            scanOptions.setOrientationLocked(false)
                            scanLauncher.launch(scanOptions)
                        }
                    ) {
                        Text(text = "Escanear QR",
                            color = Color.White )
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
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFFffffff),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedContainerColor = Color.White,//bloquea el color del fondo
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = apellido,
                        onValueChange = { apellido = it },
                        label = { Text("Apellido") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedContainerColor = Color.White,//bloquea el color del fondo
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedContainerColor = Color.White,//bloquea el color del fondo
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        modifier = Modifier.fillMaxWidth()
                    )
                    runCedula?.let {
                        OutlinedTextField(
                            value = it,
                            onValueChange = { runCedula },
                            label = { Text("Cedula") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFFFF8E6 ),
                                unfocusedContainerColor = Color.White,//bloquea el color del fondo
                                disabledContainerColor = Color.Gray,
                                errorContainerColor = Color.Red),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedContainerColor = Color.White,//bloquea el color del fondo
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = passwordConfirmation,
                        onValueChange = { passwordConfirmation = it },
                        label = { Text("Confirmar Contraseña") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedContainerColor = Color.White,//bloquea el color del fondo
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = { Text("Dirección") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedContainerColor = Color.White,//bloquea el color del fondo
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Registration Buttons
                    // Botones de Registro y Cancelar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(231, 107, 3) // Naranja
                            )
                        ) {
                            Text(
                                "Registrar",
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(231, 107, 3), // Naranja, 140, 0) // Color del texto naranja
                            ),
                            border = BorderStroke(1.dp, Color(231, 107, 3)) // Borde naranja
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