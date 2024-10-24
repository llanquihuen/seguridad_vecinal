package cl.app.seguridad.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.R

data class EmergencyOption(
    val icon: Int,
    val title: String,
    val onClick: () -> Unit = {}  // Valor por defecto para la vista previa
)

@Composable
fun EmergencyButton(
    option: EmergencyOption,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)  // Agregado padding para mejor espaciado
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        androidx.compose.material3.IconButton(
            onClick = option.onClick,
            interactionSource = interactionSource,
            modifier = Modifier
                .aspectRatio(1.5f)
                .size(50.dp)
                .align(Alignment.TopCenter)
                .clip(CircleShape)
                .indication(interactionSource, rememberRipple(
                    bounded = false,
                    radius = 40.dp,
                    color = Color(0xFF2196F3)  // Color del ripple personalizado
                ))
        ) {
            Image(
                painter = painterResource(id = option.icon),
                contentDescription = option.title,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = option.title,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .aspectRatio(3f)
                .align(Alignment.BottomCenter),
            maxLines = 2  // Permitir 2 líneas para títulos largos
        )
    }
}

@Composable
fun GridMenu(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),  // Padding general del grid
        verticalArrangement = Arrangement.spacedBy(16.dp)  // Espacio vertical entre filas
    ) {
        val options = listOf(
            listOf(
                EmergencyOption(
                    icon = R.drawable.asalto,
                    title = "Asalto"
                ),
                EmergencyOption(
                    icon = R.drawable.botiquin,
                    title = "Emergencia médica"
                ),
                EmergencyOption(
                    icon = R.drawable.robocasa,
                    title = "Robo de casa"
                )
            ),
            listOf(
                EmergencyOption(
                    icon = R.drawable.incendio,
                    title = "Incendio"
                ),
                EmergencyOption(
                    icon = R.drawable.ic_suspicious,
                    title = "Persona sospechosa"
                ),
                EmergencyOption(
                    icon = R.drawable.ic_car_theft,
                    title = "Robo de vehículo"
                )
            )
        )

        options.forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowOptions.forEach { option ->
                    EmergencyButton(
                        option = option,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

// Vista previa para EmergencyButton
@Preview(
    name = "Emergency Button Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 120,
    heightDp = 120
)
@Composable
fun EmergencyButtonPreview() {
    EmergencyButton(
        option = EmergencyOption(
            icon = R.drawable.ic_assault_02,
            title = "Asalto"
        )
    )
}

// Vista previa para GridMenu
@Preview(
    name = "Grid Menu Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 320,
    heightDp = 480
)
@Composable
fun GridMenuPreview() {
    GridMenu()
}