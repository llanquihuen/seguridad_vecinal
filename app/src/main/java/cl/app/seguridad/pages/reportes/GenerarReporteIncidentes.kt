package cl.app.seguridad.pages.reportes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.app.seguridad.TopBox
import cl.app.seguridad.R
import cl.app.seguridad.pages.cameras.NavItem
import cl.app.seguridad.pages.cameras.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerarReporteIncidentes(
    onNavigate: (String) -> Unit = {},
    onVolver: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var tipoIncidente by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fechaHora by remember { mutableStateOf("") }

    val navigationItems = listOf(
        NavItem("Home", Icons.Default.Home, 0, Screen.Home.route),
        NavItem("Ubicacion", Icons.Default.LocationOn, 0, Screen.Ubicacion.route),
        NavItem("Historial", Icons.Default.DateRange, 0, Screen.Historial.route),
        NavItem("Chat", Icons.Default.Notifications, 5, Screen.Chat.route),
        NavItem("Perfil", Icons.Default.Person, 0, Screen.Perfil.route)
    )

    var selectedItem by remember { mutableStateOf(0) }

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
            Text(
                text = "Reporte de incidentes",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xFFFF6B00),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Tipo de Incidente
            OutlinedTextField(
                value = tipoIncidente,
                onValueChange = { tipoIncidente = it },
                label = { Text("Tipo de Incidente") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowDropDown, "Mostrar opciones")
                    }
                }
            )

            // Descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción del Incidente") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(vertical = 8.dp),
                maxLines = 5,
                placeholder = { Text("Describa el incidente...") }
            )

            // Fecha y Hora
            OutlinedTextField(
                value = fechaHora,
                onValueChange = { fechaHora = it },
                label = { Text("Fecha y Hora") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.DateRange, "Seleccionar fecha y hora")
                    }
                }
            )

            // Adjuntar Fotos/Videos
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Adjuntar Fotos/Videos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Add, "Adjuntar archivos")
                    }
                },
                enabled = false
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6B00)
                    )
                ) {
                    Text("Enviar Reporte")
                }

                Button(
                    onClick = onVolver,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF333333)
                    )
                ) {
                    Text("Volver")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenerarReporteIncidentesPreview() {
    MaterialTheme {
        GenerarReporteIncidentes()
    }
}