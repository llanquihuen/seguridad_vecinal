package cl.app.seguridad.pages.home

import LocationManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationImportant
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.app.seguridad.pages.AlertHistoryViewModel

@Preview(showBackground = true)
@Composable
fun PanicButtons(
    modifier: Modifier = Modifier,
    viewModel: AlertHistoryViewModel = viewModel()
) {
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showAlertSentScreen by remember { mutableStateOf(false) }
    var showSilentConfirmDialog by remember { mutableStateOf(false) }
    var selectedEmergency by remember { mutableStateOf<EmergencyOption?>(null) }

    // Nuevas variables para location
    var currentLocation by remember { mutableStateOf<LocationManager.DetailedLocation?>(null) }
    var isLoadingLocation by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val locationManager = remember { LocationManager(context) }
    val scope = rememberCoroutineScope()


    // Función para obtener ubicación
    fun getLocation() {
        isLoadingLocation = true
        scope.launch {
            try {
                println("Iniciando obtención de ubicación")
                currentLocation = locationManager.getDetailedLocation()
                println("Ubicación obtenida: $currentLocation")
                if (currentLocation == null) {
                    currentLocation = LocationManager.DetailedLocation(
                        address = "Ubicación no disponible",
                        street = "",
                        number = "",
                        commune = "",
                        city = ""
                    )
                }
            } catch (e: Exception) {
                println("Error al obtener ubicación: ${e.message}")
                currentLocation = LocationManager.DetailedLocation(
                    address = "Error al obtener ubicación",
                    street = "",
                    number = "",
                    commune = "",
                    city = ""
                )
            } finally {
                isLoadingLocation = false
            }
        }
    }

    // Definir las opciones de emergencia
    val panicOption = EmergencyOption(
        icon = Icons.Default.NotificationImportant.hashCode(),
        title = "Pánico",
        description = "Se ha generado una alerta de pánico",
        actionDescription = "Se notificará inmediatamente a Carabineros y vecinos cercanos",
        confirmationMessage = "¿Deseas enviar una alerta de pánico?",
        alertColor = Color(0xFFFF4444)
    )

    val silentPanicOption = EmergencyOption(
        icon = Icons.Default.VolumeOff.hashCode(),
        title = "Pánico Silencioso",
        description = "Se ha generado una alerta silenciosa",
        actionDescription = "Se notificará discretamente a Carabineros",
        confirmationMessage = "¿Deseas enviar una alerta silenciosa?",
        alertColor = Color(0xFF9E9E9E)
    )

    if (showAlertSentScreen && selectedEmergency != null) {
        AlertSentScreen(
            emergency = selectedEmergency!!,
            location = when {
                currentLocation != null -> buildString {
                    if (!currentLocation?.street.isNullOrEmpty()) {
                        append(currentLocation?.street)
                        if (!currentLocation?.number.isNullOrEmpty()) {
                            append(" ${currentLocation?.number}")
                        }
                    }
                    if (!currentLocation?.commune.isNullOrEmpty()) {
                        append(", ${currentLocation?.commune}")
                    }
                    if (!currentLocation?.city.isNullOrEmpty()) {
                        append(", ${currentLocation?.city}")
                    }
                }.takeIf { it.isNotEmpty() } ?: "Ubicación no disponible"
                else -> "Obteniendo ubicación..."
            },
            onDismiss = {
                showAlertSentScreen = false
                selectedEmergency = null
                currentLocation = null
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .shadow(
                    elevation = 9.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF4444)
            ),
            onClick = {
                selectedEmergency = panicOption
                showConfirmDialog = true
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.NotificationImportant,
                    contentDescription = "Icono de sirena",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "PÁNICO",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Button(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .shadow(
                    elevation = 9.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9E9E9E)
            ),
            onClick = {
                selectedEmergency = silentPanicOption
                showSilentConfirmDialog = true
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeOff,
                    contentDescription = "Icono de silencio",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "PÁNICO SILENCIOSO",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

    // Diálogo de confirmación para pánico normal
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color(0xFFFF4444),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("¿Deseas enviar una alerta de pánico?")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        getLocation()
                        viewModel.addAlert(panicOption, when {
                            currentLocation != null -> buildString {
                                if (!currentLocation?.street.isNullOrEmpty()) {
                                    append(currentLocation?.street)
                                    if (!currentLocation?.number.isNullOrEmpty()) {
                                        append(" ${currentLocation?.number}")
                                    }
                                }
                                if (!currentLocation?.commune.isNullOrEmpty()) {
                                    append(", ${currentLocation?.commune}")
                                }
                                if (!currentLocation?.city.isNullOrEmpty()) {
                                    append(", ${currentLocation?.city}")
                                }
                            }.takeIf { it.isNotEmpty() } ?: "Ubicación no disponible"
                            else -> "Obteniendo ubicación..."
                        })
                        showConfirmDialog = false
                        showAlertSentScreen = true
                        selectedEmergency = panicOption
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF4444)
                    ),
                    enabled = !isLoadingLocation
                ) {
                    if (isLoadingLocation) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text("Enviar")
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = { showConfirmDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Diálogo de confirmación para pánico silencioso
    if (showSilentConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showSilentConfirmDialog = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeOff,
                        contentDescription = null,
                        tint = Color(0xFF9E9E9E),
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("¿Deseas enviar una alerta silenciosa?")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        getLocation()
                        viewModel.addAlert(silentPanicOption, when {
                            currentLocation != null -> buildString {
                                if (!currentLocation?.street.isNullOrEmpty()) {
                                    append(currentLocation?.street)
                                    if (!currentLocation?.number.isNullOrEmpty()) {
                                        append(" ${currentLocation?.number}")
                                    }
                                }
                                if (!currentLocation?.commune.isNullOrEmpty()) {
                                    append(", ${currentLocation?.commune}")
                                }
                                if (!currentLocation?.city.isNullOrEmpty()) {
                                    append(", ${currentLocation?.city}")
                                }
                            }.takeIf { it.isNotEmpty() } ?: "Ubicación no disponible"
                            else -> "Obteniendo ubicación..."
                        })
                        showSilentConfirmDialog = false
                        showAlertSentScreen = true
                        selectedEmergency = silentPanicOption
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9E9E9E)
                    ),
                    enabled = !isLoadingLocation
                ) {
                    if (isLoadingLocation) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text("Enviar")
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = { showSilentConfirmDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun AlertSentScreen(
    emergency: EmergencyOption,
    location: String,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "ALERTA COMUNITARIA",
            style = MaterialTheme.typography.headlineMedium,
            color = emergency.alertColor
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    emergency.description,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Ubicación del incidente:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    location,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    emergency.actionDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF4CAF50)
                )
            }
        }

        Button(
            onClick = onDismiss,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            )
        ) {
            Text("Volver")
        }
    }
}