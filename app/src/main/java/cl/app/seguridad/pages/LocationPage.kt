package cl.app.seguridad.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import cl.app.seguridad.pages.home.EmergencyOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

@Composable
fun LocationPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1976D2)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mapa",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

class AlertHistoryViewModel : ViewModel() {
    private val _alerts = MutableStateFlow<List<AlertHistoryItem>>(emptyList())
    val alerts = _alerts.asStateFlow()

    fun addAlert(emergency: EmergencyOption, location: String) {
        val newAlert = AlertHistoryItem(
            type = emergency.title,
            description = emergency.description,
            location = location,
            timestamp = Date(),
            icon = emergency.icon,
            alertColor = emergency.alertColor
        )
        _alerts.value = listOf(newAlert) + _alerts.value
    }
}