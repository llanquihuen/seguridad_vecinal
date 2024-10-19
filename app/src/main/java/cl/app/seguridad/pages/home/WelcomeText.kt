package cl.app.seguridad.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.R

@Preview
@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Column (modifier = Modifier.padding(top = 20.dp)){
        Row {
            Box() {
                Text(
                    text = "Hola Cindy,\nEn caso de alguna emergencia, por favor presione el botón correspondiente. " +
                            "Se generará una alerta a tu comunidad vecinal, y quedará un registro en el historial",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(20.dp,10.dp),
                    color = Color.Black
                )
            }
        }
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Column (modifier = Modifier.weight(2f)) {
                Text(
                    text = "En esta app, tendras acceso: ",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(20.dp,10.dp),
                    color = Color.Black
                )
                Text(
                    text = "• Cámaras de videovigilancia\n" +
                            "• Botones para generar alertas\n" +
                            "• Chat de la comunidad",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(20.dp,0.dp),
                    color = Color.Black
                )
            }
            Column (modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.logo_gradacion_horizontal),
                    contentDescription = "Mi imagen",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}