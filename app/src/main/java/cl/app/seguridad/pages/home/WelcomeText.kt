package cl.app.seguridad.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WelcomeText(modifier: Modifier = Modifier) {

    Column {
        Row {
            Box() {
                Text(
                    text = "Hola Cindy, En caso de alguna emergencia, por favor presione el botón correspondiente. " +
                            "Se generará una alerta a tu comunidad vecinal, y quedará un registro en el historial",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(20.dp,10.dp),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
        Row {
            Box() {
                Column {
                    Text(
                        text = "En esta app, tendras acceso: " +
                                "Cámaras de videovigilancia" +
                                "Botones para generar alertas" +
                                "Chat de la comunidad",
                        fontSize = 17.sp,
                        modifier = Modifier.padding(20.dp,10.dp),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                Column {
                    Box(
                        modifier = Modifier
                            .background(Color.LightGray) // Color de fondo como marcador de posición
                    ) {
                        // ... Tu código para cargar la imagen real
                    }
                }
            }
        }
    }
}