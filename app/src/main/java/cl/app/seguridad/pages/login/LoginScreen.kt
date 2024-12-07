import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.app.seguridad.R
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.app.seguridad.pages.cameras.Screen
import cl.app.seguridad.pages.login.Home
import cl.app.seguridad.pages.login.RegisterQR

enum class LoginRegisterRoute(){
    Login,
    RegisterQR,
    RegisterData
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit
//    currentScreen: String,
//    onScreenChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF87CEEB),
                            Color(0xFFFFFFFF)
                        )
                    ))
                .clickable { focusManager.clearFocus() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.fondologin),
                contentDescription = "Splash Screen Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White.copy(alpha = 0.1f)),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val emailState = remember { mutableStateOf("") }
                    val passwordState = remember { mutableStateOf("") }
                    Spacer(modifier = Modifier.height(36.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logoseguridad),
                        contentDescription = "Logo",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .aspectRatio(1f)
                            .padding(0.dp, 30.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(56.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Email",
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.DarkGray,
                            textAlign = TextAlign.Left
                        )
                    }
                    TextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Email") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Contraseña",
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.DarkGray,
                            textAlign = TextAlign.Left
                        )
                    }
                    TextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            errorContainerColor = Color.Red
                        )
                    )

                    Spacer(modifier = Modifier.height(46.dp))

                    Button(
                        onClick = { navigateToHome() },
                        modifier = Modifier.fillMaxWidth(0.7f),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color(222, 252, 244)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(1, 147, 157),
                            contentColor = Color(255, 255,255,255)
                        )
                    ) {
                        Text("Iniciar Sesión")
                    }

                    // Trigger the registration bottom sheet
                    ClickableText(
                        text = AnnotatedString("Registrarse"),
                        onClick = {navigateToRegister()},
                        modifier = Modifier.padding(top = 16.dp),
                        style = TextStyle(color = Color.Black)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 80.dp, 0.dp, 0.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            ClickableText(
                                text = AnnotatedString("¿Olvidaste tu Contraseña?"),
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(bottom = 8.dp),
                                style = TextStyle(color = Color.Black)
                            )
                        }
                        Column {
                            Text(
                                text = "V1.0",
                                color = Color.Black,
                                textAlign = TextAlign.Right
                            )
                        }
                    }
                }
            }
        }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(
        {navController.navigate(Home)},
        {navController.navigate(RegisterQR)}
    )
}
