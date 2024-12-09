package cl.app.seguridad.pages.home

import androidx.compose.ui.graphics.Color

data class EmergencyOption(
    val icon: Int,
    val title: String,
    val description: String,
    val actionDescription: String,
    val confirmationMessage: String,
    val alertColor: Color = Color(0xFFFF4444)
)
