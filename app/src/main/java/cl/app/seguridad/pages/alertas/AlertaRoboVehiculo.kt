package cl.app.seguridad.pages.alertas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.app.seguridad.TopBox
import cl.app.seguridad.R
import cl.app.seguridad.pages.cameras.NavItem
import cl.app.seguridad.pages.cameras.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertaRoboVehiculo(
    onNavigate: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(0) }

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
            NavigationBar {
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
                                    badge = { Badge { Text(item.badgeCount.toString()) } }
                                ) {
                                    Icon(item.icon, item.title)
                                }
                            } else {
                                Icon(item.icon, item.title)
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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título Alerta Comunitaria
            Text(
                text = "ALERTA COMUNITARIA",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xFFFF6B00),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Imagen del incidente
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cam),
                    contentDescription = "Imagen del incidente",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Descripción del incidente
            Text(
                text = "Se generó una alerta por",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "robo de vehículo (portonazo)",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            // Información de registro
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Registrado en la ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "CAM3",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFFFF6B00)
                    )
                )
                Text(
                    text = " a las ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "00:23",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFFFF6B00)
                    )
                )
            }

            Text(
                text = "Se reportó a Carabineros",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Ubicación del incidente
            Text(
                text = "Ubicación del incidente",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF4CAF50)
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Mapa
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mapa),
                    contentDescription = "Mapa del incidente",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Icono del tipo de incidente
            Card(
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 16.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = "Robo de vehículo",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Text(
                text = "Robo de\nvehículo",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertaRoboVehiculoPreview() {
    MaterialTheme {
        AlertaRoboVehiculo()
    }
}