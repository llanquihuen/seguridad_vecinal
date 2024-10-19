package cl.app.seguridad.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.pages.home.GridMenu
import cl.app.seguridad.pages.home.PanicButtons
import cl.app.seguridad.pages.home.WelcomeText

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 125.dp)
            .background(Color.White).verticalScroll(state = scrollState),
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
        PanicButtons()
        GridMenu()

    }

}