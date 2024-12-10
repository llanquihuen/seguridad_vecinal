package cl.app.seguridad.pages.register

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import cl.app.seguridad.api.RetrofitClient.apiService
import cl.app.seguridad.api.RetrofitClient.registerUserService
import cl.app.seguridad.data.Usuario
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RegisterScreen(
    navigateToLogin: () -> Unit,
) {
    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA
        )
    )

    LaunchedEffect(key1 = Unit) {
        permissions.launchMultiplePermissionRequest()
    }

    val coroutineScope = rememberCoroutineScope()
    var showPopup by remember { mutableStateOf(false) }
    var showSuccessPopup by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var dataCedula by remember { mutableStateOf("---") }
    var runCedula by remember { mutableStateOf<String?>(null) }


    var usuario by remember {
        mutableStateOf(
            Usuario(
                nombre = "",
                apellido = "",
                email = "",
                rut = "",
                password = "",
                passwordConfirmation = "",
                estadoCuenta = false,
                fechaRegistro = LocalDate.now().toString(),
                direccion = "",
                latitud = 0.0,
                longitud = 0.0,
                verificado = false,
                tokenVerificacion = ""
            )
        )
    }
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    val isEmailValid by derivedStateOf { usuario.email.matches(emailRegex) }
    val arePasswordsMatching by derivedStateOf { usuario.password == usuario.passwordConfirmation }
    val isFormValid by derivedStateOf { arePasswordsMatching && isEmailValid && usuario.password.isNotEmpty()  }

    val scanLauncher = rememberLauncherForActivityResult (
        contract = ScanContract(),
        onResult = {
            result -> dataCedula = result.contents ?: "---"
            runCedula = extractAndVerify(result.contents) ?: null
            var existRut = false

            if (runCedula != null) {
                usuario = usuario.copy(rut = runCedula!!)
                coroutineScope.launch {
                    existRut = fetchUserData(runCedula!!)
                    if (existRut) {
                        runCedula = null
                        showPopup = true
                    }
                }
            }
        }
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showPopup) {
            RutExistsPopup(onDismiss = { showPopup = false })
        }
        if(showSuccessPopup){
            SuccessPopup(onDismiss = { navigateToLogin() })
        }
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
                    if (errorMessage != null) {
                        Text(
                            text = errorMessage ?: "",
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    OutlinedTextField(
                        value = usuario.nombre,
                        onValueChange = { nuevoNombre ->
                            usuario = usuario.copy(nombre = nuevoNombre) // Usas un método de la clase para actualizar
                        },
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
                        value = usuario.apellido,
                        onValueChange = { nuevoApellido ->
                            usuario = usuario.copy(apellido = nuevoApellido) // Usas un método de la clase para actualizar
                        },
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
                        value = usuario.email,
                        onValueChange = { nuevoEmail ->
                            usuario = usuario.copy(email = nuevoEmail) // Usas un método de la clase para actualizar
                        },
                        label = { Text("Email") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedIndicatorColor = if (isEmailValid) Color.Black else Color.Red,
                            focusedIndicatorColor = if (isEmailValid) MaterialTheme.colorScheme.primary else Color.Red,
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
                        value = usuario.password,
                        onValueChange = { nuevoPassword ->
                            usuario = usuario.copy(password = nuevoPassword) // Usas un método de la clase para actualizar
                        },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedIndicatorColor = if (arePasswordsMatching) Color.Black else Color.Red,
                            focusedIndicatorColor = if (arePasswordsMatching) MaterialTheme.colorScheme.primary else Color.Red,
                            unfocusedContainerColor =  Color.White,
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = usuario.passwordConfirmation,
                        onValueChange = { nuevoPasswordConfirmation ->
                            usuario = usuario.copy(passwordConfirmation = nuevoPasswordConfirmation) // Usas un método de la clase para actualizar
                        },
                        label = { Text("Confirmar Contraseña") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFF8E6 ),
                            unfocusedIndicatorColor = if (arePasswordsMatching) Color.Black else Color.Red,
                            focusedIndicatorColor = if (arePasswordsMatching) MaterialTheme.colorScheme.primary else Color.Red,
                            unfocusedContainerColor =  Color.White,
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = usuario.direccion,
                        onValueChange = { nuevoDireccion ->
                            usuario = usuario.copy(direccion = nuevoDireccion) // Usas un método de la clase para actualizar
                        },
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
                            onClick = {
                                coroutineScope.launch {
                                    val resultado = registerUser(usuario)
                                    if (resultado) {
                                        showSuccessPopup = true
                                    } else {
                                        errorMessage = "Error al registrar el usuario. Verifique sus datos."
                                    }
                                }
                            },
                            enabled = isFormValid,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isFormValid) Color(231, 107, 3)  else Color.Gray
                            )
                        ) {
                            Text(
                                "Registrar",
                                color = Color.White
                            )
                        }


                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedButton(
                            onClick = {runCedula = null},
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

// Popup for existing RUT
@Composable
fun RutExistsPopup(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "El RUT ingresado ya existe",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    "Este RUT ya está registrado en el sistema. Por favor, utilice un RUT diferente.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(231, 107, 3)
                    )
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}
@Composable
fun SuccessPopup(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Nuevo Registro",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    "Su usuario ha sido registrado exitosamente, su cuenta se encuentra en espera de su verificación, le llegara un email con los detalles de su cuenta.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(231, 107, 3)
                    )
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}

suspend fun fetchUserData(rut: String): Boolean {
    return try {
        val response = apiService.findRut(rut) // Asegúrate de que esto retorne un `ResponseBody` o `String`
        val responseString = response.string() // Convierte el cuerpo de la respuesta a un String
        Log.v("MyTag", "Response: $responseString") // Log the response
        responseString.contains("encontrado")
    } catch (e: Exception) {
        Log.e("MyTag", "Error fetching user data", e)
        false
    }
}

suspend fun registerUser(usuario: Usuario): Boolean {
    return try {
        val response = registerUserService.register(usuario)
        if (response.isSuccessful) {
            true // Registro exitoso
        } else {
            Log.e("RegisterError", "Error: ${response.errorBody()?.string()}")
            false // Error en el registro
        }
    } catch (e: Exception) {
        Log.e("RegisterException", "Exception: ${e.message}")
        false // Error de red o de otra naturaleza
    }
}

fun extractAndVerify(text: String): String? {
    if (!text.contains("CEDULA")) {return null}
    val regex = Regex("""RUN=(\d{6,8}-(?:\d|[kK]))""")
    val matchResult = regex.find(text)
    println(matchResult)
    return matchResult?.groupValues?.getOrNull(1)
}