package cl.app.seguridad.pages.cameras

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.app.seguridad.TopBox
import cl.app.seguridad.R

// Sealed class para las rutas de navegación
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Ubicacion : Screen("ubicacion")
    object Historial : Screen("historial")
    object Chat : Screen("chat")
    object Perfil : Screen("perfil")
}

// Clase de datos para los items de navegación
data class NavItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val badgeCount: Int = 0,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCam(
    onNavigate: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()

    // Lista de items de navegación
    val navigationItems = listOf(
        NavItem("Home", Icons.Default.Home, 0, Screen.Home.route),
        NavItem("Ubicacion", Icons.Default.LocationOn, 0, Screen.Ubicacion.route),
        NavItem("Historial", Icons.Default.DateRange, 0, Screen.Historial.route),
        NavItem("Chat", Icons.Default.Notifications, 5, Screen.Chat.route),
        NavItem("Perfil", Icons.Default.Person, 0, Screen.Perfil.route)
    )

    Scaffold(
        topBar = {
            TopBox("Seguridad Vecinal")
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            onNavigate(item.route)
                        },
                        icon = {
                            if (item.badgeCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge {
                                            Text(item.badgeCount.toString())
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            }
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cámaras Grid
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                // Cámara Principal
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.cam),
                            contentDescription = "Cámara Principal",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = "EN VIVO",
                            color = Color.Red,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.TopEnd)
                        )
                    }
                }

                // Segunda Cámara
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.cam),
                            contentDescription = "Cámara Secundaria",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = "EN VIVO",
                            color = Color.Red,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.TopEnd)
                        )
                    }
                }

                // Controles de reproducción
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconButton(
                        onClick = { },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFF00BCD4)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White
                        )
                    }
                    FilledIconButton(
                        onClick = { },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFFFF9800)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Pause",
                            tint = Color.White
                        )
                    }
                    FilledIconButton(
                        onClick = { },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Color(0xFF333333)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Stop",
                            tint = Color.White
                        )
                    }
                }

                Text(
                    text = "Listado de Cámaras",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Listado de cámaras con botones
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00BCD4)
                    )
                ) {
                    Text("Cámara 1: Entrada Principal")
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800)
                    )
                ) {
                    Text("Cámara 2: Intersección calles 1/2")
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF333333)
                    )
                ) {
                    Text("Cámara 3: Acceso vehicular")
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF666666)
                    )
                ) {
                    Text("Cámara 4: Plaza (sector juegos)")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListCamPreview() {
    MaterialTheme {
        ListCam()
    }
}