package cl.app.seguridad.pages.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.R

//@Preview
@Composable
fun GridMenu(modifier: Modifier = Modifier) {
    Column {
        Row (modifier = Modifier.padding(0.dp,10.dp)){
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mainmenu_incendio), // Reemplaza con tu icono
                    contentDescription = "Descripción del icono",
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp) // Ajusta el tamaño del icono
                        .align(Alignment.TopCenter) // Alinea el icono en el centro
                )
                Text(
                    text = "Asalto",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .aspectRatio(3f)
                        .align(Alignment.BottomCenter)
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mainmenu_incendio), // Reemplaza con tu icono
                    contentDescription = "Descripción del icono",
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp) // Ajusta el tamaño del icono
                        .align(Alignment.TopCenter) // Alinea el icono en el centro
                )
                Text(
                    text = "Emergencia médica",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .aspectRatio(3f)
                        .align(Alignment.BottomCenter)
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mainmenu_incendio), // Reemplaza con tu icono
                    contentDescription = "Descripción del icono",
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp) // Ajusta el tamaño del icono
                        .align(Alignment.TopCenter) // Alinea el icono en el centro
                )
                Text(
                    text = "Robo de casa",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .aspectRatio(3f)
                        .align(Alignment.BottomCenter)
                )
            }
        }
        Row {
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mainmenu_incendio), // Reemplaza con tu icono
                    contentDescription = "Descripción del icono",
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp) // Ajusta el tamaño del icono
                        .align(Alignment.TopCenter) // Alinea el icono en el centro
                )
                Text(
                    text = "Incendio",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .aspectRatio(3f)
                        .align(Alignment.BottomCenter)
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mainmenu_incendio), // Reemplaza con tu icono
                    contentDescription = "Descripción del icono",
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp) // Ajusta el tamaño del icono
                        .align(Alignment.TopCenter) // Alinea el icono en el centro
                )
                Text(
                    text = "Persona sospechosa",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .aspectRatio(3f)
                        .align(Alignment.BottomCenter)
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_mainmenu_incendio), // Reemplaza con tu icono
                    contentDescription = "Descripción del icono",
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp) // Ajusta el tamaño del icono
                        .align(Alignment.TopCenter) // Alinea el icono en el centro
                )
                Text(
                    text = "Robo de vehículo",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .aspectRatio(3f)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}