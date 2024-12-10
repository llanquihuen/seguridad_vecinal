package cl.app.seguridad.pages.home

import LocationManager
import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.R
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.app.seguridad.pages.AlertHistoryViewModel


@Composable
private fun EmergencyButton(
    option: EmergencyOption,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
             // Tamaño fijo para hacer los botones cuadrados y más pequeños
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        // Distribuye el espacio entre los elementos
        ) {
            Icon(
                painter = painterResource(id = option.icon),
                contentDescription = option.title,
                modifier = Modifier
                    .size(62.dp)
                    .padding(top = 4.dp),
                tint = Color.Unspecified
            )

            Text(
                text = option.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp  // Tamaño de texto más pequeño
                ),
                maxLines = 2,
                minLines = 2,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridMenu(modifier: Modifier = Modifier,
             viewModel: AlertHistoryViewModel = viewModel()
) {
    var permissionsGranted by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionsGranted = permissions.values.all { it }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
    var selectedEmergency by remember { mutableStateOf<EmergencyOption?>(null) }
    var showAlertSentScreen by remember { mutableStateOf(false) }
    var currentLocation by remember { mutableStateOf<LocationManager.DetailedLocation?>(null) }

    val context = LocalContext.current
    val locationManager = remember { LocationManager(context) }
    val scope = rememberCoroutineScope()

    var isLoadingLocation by remember { mutableStateOf(false) }

    fun getLocation() {
        isLoadingLocation = true
        scope.launch {
            try {
                println("Iniciando obtención de ubicación")
                currentLocation = locationManager.getDetailedLocation()
                println("Ubicación obtenida: $currentLocation")
                if (currentLocation == null) {
                    // Si la ubicación es nula, asignar una ubicación por defecto
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
                // Si hay un error, asignar una ubicación por defecto
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
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val options = listOf(
                listOf(
                    EmergencyOption(
                        icon = R.drawable.asalto,
                        title = "Asalto",
                        description = "Se ha reportado un asalto en curso",
                        actionDescription = "Se notificará inmediatamente a Carabineros y vecinos cercanos",
                        confirmationMessage = "¿Deseas reportar un asalto en curso?"
                    ),
                    EmergencyOption(
                        icon = R.drawable.botiquin,
                        title = "Emergencia médica",
                        description = "Se requiere asistencia médica urgente",
                        actionDescription = "Se contactará al servicio de emergencias médicas más cercano",
                        confirmationMessage = "¿Necesitas asistencia médica urgente?"
                    ),
                    EmergencyOption(
                        icon = R.drawable.robocasa,
                        title = "Robo de casa",
                        description = "Se ha detectado un intento de robo a propiedad",
                        actionDescription = "Se alertará a Carabineros y la red vecinal de seguridad",
                        confirmationMessage = "¿Deseas reportar un robo a propiedad?"
                    )
                ),
                listOf(
                    EmergencyOption(
                        icon = R.drawable.incendio,
                        title = "Incendio",
                        description = "Se ha reportado un incendio en la zona",
                        actionDescription = "Se notificará a Bomberos y servicios de emergencia",
                        confirmationMessage = "¿Deseas reportar un incendio?"
                    ),
                    EmergencyOption(
                        icon = R.drawable.sospecha,
                        title = "Persona sospechosa",
                        description = "Se ha detectado actividad sospechosa",
                        actionDescription = "Se alertará a la red vecinal y patrullas cercanas",
                        confirmationMessage = "¿Deseas reportar actividad sospechosa?"
                    ),
                    EmergencyOption(
                        icon = R.drawable.vehiculo,
                        title = "Robo de vehículo",
                        description = "Se ha reportado un robo de vehículo",
                        actionDescription = "Se notificará a Carabineros y se activará alerta vehicular",
                        confirmationMessage = "¿Deseas reportar un robo de vehículo?"
                    )
                )
            )

            options.forEach { rowOptions ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowOptions.forEach { option ->
                        EmergencyButton(
                            option = option,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            onClick = {
                                selectedEmergency = option
                            }
                        )
                    }
                }
            }

            // Diálogo de confirmación
            selectedEmergency?.let { emergency ->
                AlertDialog(
                    onDismissRequest = { selectedEmergency = null },
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = emergency.icon),
                                contentDescription = null,
                                modifier = Modifier.size(36.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(emergency.confirmationMessage)
                        }
                    },
                    text = {
                        Text(emergency.actionDescription)
                    },
                    // AQUÍ es donde debe ir el nuevo código del confirmButton
                    confirmButton = {
                        Button(
                            onClick = {
                                getLocation()
                                viewModel.addAlert(emergency, when {
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
                                showAlertSentScreen = true
                                selectedEmergency = emergency
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = emergency.alertColor
                            ),
                            enabled = !isLoadingLocation
                        ) {
                            if (isLoadingLocation) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = Color.White
                                )
                            } else {
                                Text("Confirmar")
                            }
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { selectedEmergency = null },
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
    }
}