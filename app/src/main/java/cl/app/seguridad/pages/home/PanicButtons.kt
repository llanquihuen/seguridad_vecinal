package cl.app.seguridad.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PanicButtons (){
    val rojoOscuro = Color(0xFFA61C3C)
    Row(
    modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp,25.dp),
    horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button (modifier = Modifier.defaultMinSize(minWidth = 150.dp, minHeight = 60.dp)
            .shadow(
                elevation =9.dp, // Define la elevación de la sombra
                shape = RoundedCornerShape(25.dp), // Define la forma de la sombra (opcional)
                clip = true),
            colors = ButtonColors(rojoOscuro, Color.White, Color.Gray,Color.DarkGray),
            onClick = { /* Acción para el botón "Pánico" */ })
        {
            Text("Pánico")        }
        Button(modifier = Modifier.defaultMinSize(minWidth = 150.dp, minHeight = 60.dp)
            .shadow(
                elevation =9.dp, // Define la elevación de la sombra
                shape = RoundedCornerShape(25.dp), // Define la forma de la sombra (opcional)
                clip = true),
            onClick = { /* Acción para el botón "Pánico silencioso" */ }) {
            Text("Pánico silencioso")
        }
    }
}