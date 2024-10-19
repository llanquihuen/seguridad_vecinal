package cl.app.seguridad.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Preview
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
        Row (modifier = Modifier.padding(0.dp,10.dp)){
            Box(modifier = Modifier
                .weight(1f)
                //.border(width = 2.dp, color = Color.Red)
                .aspectRatio(1f)) {
                val interactionSource = remember { MutableInteractionSource() }
                IconButton(
                    onClick = { /* Acción al hacer clic en la imagen */ },
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .aspectRatio(1.5f)
                        .size(50.dp)
                        .clip(CircleShape)
                        .indication(interactionSource, rememberRipple(bounded = false, radius = 40.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fuego),
                        contentDescription = "Descripción del icono",
                        modifier = Modifier.fillMaxSize() // Ajusta el tamaño de la imagen al botón
                    )
                }
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