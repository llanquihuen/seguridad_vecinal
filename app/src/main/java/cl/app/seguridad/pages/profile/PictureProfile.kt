package cl.app.seguridad.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.app.seguridad.R

@Preview(showBackground = true)
@Composable
fun PictureProfile (modifier: Modifier = Modifier) {
    Column (modifier = Modifier
        .padding(20.dp, 40.dp)
        .size(200.dp)
        .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Box(modifier = Modifier
            .padding(10.dp))
//            .shadow(6.dp, clip = true, shape = CircleShape))
            {
            Image(
                contentDescription = "Mi imagen",
                painter = painterResource(id = R.drawable.profile_pic),
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .shadow(elevation = 9.dp, shape = CircleShape, clip = true),
            )
            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                .align(Alignment.BottomEnd) // Coloca el botón en la esquina inferior derecha
                .padding( 0.dp, 0.dp,20.dp,10.dp) // Agrega un espacio alrededor del botón
                .size(25.dp) // Ajusta el tamaño del botón
                .clip(CircleShape)
                    .background(Color.Black)
                    .border(2.dp, Color.LightGray, CircleShape)
            ){
                Icon(
                    imageVector = Icons.Filled.Edit, // Usa el icono de lápiz de Material Icons
                    contentDescription = "Editar foto",
                    tint = Color.White // Cambia el color del icono si es necesario
                )
            }
        }
    }

}