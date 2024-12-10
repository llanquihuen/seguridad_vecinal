package cl.app.seguridad.pages.home

import LocationManager
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
fun GridMenu(modifier: Modifier = Modifier) {
    var selectedEmergency by remember { mutableStateOf<EmergencyOption?>(null) }
    var showAlertSentScreen by remember { mutableStateOf(false) }
    var currentLocation by remember { mutableStateOf<LocationManager.DetailedLocation?>(null) }

    val context = LocalContext.current
    val locationManager = remember { LocationManager(context) }
    val scope = rememberCoroutineScope()

    fun getLocation() {
        scope.launch {
            currentLocation = locationManager.getDetailedLocation()
        }
    }

    if (showAlertSentScreen && selectedEmergency != null) {
        AlertSentScreen(
            emergency = selectedEmergency!!,
            location = currentLocation.toString(),
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
                    confirmButton = {
                        Button(
                            onClick = {
                                getLocation()
                                showAlertSentScreen = true
                                selectedEmergency = emergency
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = emergency.alertColor
                            )
                        ) {
                            Text("Confirmar")
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