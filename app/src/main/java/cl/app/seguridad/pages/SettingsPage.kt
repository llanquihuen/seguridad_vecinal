package cl.app.seguridad.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.TopBox
import cl.app.seguridad.pages.profile.PersonalData
import cl.app.seguridad.pages.profile.PictureProfile
@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 125.dp)
            .background(Color.White),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
        TopBox(titulo = "Perfil")
    }
        item {PictureProfile()}
        item {Spacer(modifier = Modifier.height(10.dp))}
        item {PersonalData()}
    }
}
