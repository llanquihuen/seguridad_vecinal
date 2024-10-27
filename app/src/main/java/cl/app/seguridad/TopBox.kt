package cl.app.seguridad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBox(titulo: String) {
    Box(
        Modifier
            .background(Color(1, 148, 158))
            .fillMaxWidth()
    ) {
        Text(
            text = titulo, // Usa el título del parámetro
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(255, 245, 240),
            modifier = Modifier
                .padding(0.dp, 50.dp, 0.dp, 35.dp)
                .align(Alignment.Center)
                .background(Color(1, 148, 158))

        )
    }
    Spacer(modifier = Modifier.height(1.dp).fillMaxWidth()
        .background(Color(5, 205, 240)))

    Spacer(modifier = Modifier.height(3.dp)
        .fillMaxWidth()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(Color.LightGray, Color.LightGray.copy(alpha = 0f))
            )
        )
    )
}