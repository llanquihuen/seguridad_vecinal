package cl.app.seguridad.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.pages.profile.PersonalData
import cl.app.seguridad.pages.profile.PictureProfile
@Preview(showBackground = true)
@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 125.dp)
            .background(Color.White).verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .background(Color(5, 165, 200))
            .fillMaxWidth()
        ){
            Text(
                text = "Perfil",
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(255, 245, 240),
                modifier = Modifier
                    .padding(0.dp, 50.dp, 0.dp, 50.dp)
                    .align(Alignment.Center)
                    .background(Color(5, 165, 200))

            )
        }
        PictureProfile()
        Spacer(modifier = Modifier.height(40.dp))
        PersonalData()
    }
}