package cl.app.seguridad.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.pages.home.GridMenu
import cl.app.seguridad.pages.home.WelcomeText

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 160.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier
            .background(Color(5, 165, 200))
            .fillMaxWidth()
        ){
            Text(
                text = "Seguridad Vecinal",
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(255, 245, 240),
                modifier = Modifier
                    .padding(0.dp, 60.dp, 0.dp, 50.dp)
                    .align(Alignment.Center)

            )

        }
        WelcomeText()
        GridMenu()
    }

}