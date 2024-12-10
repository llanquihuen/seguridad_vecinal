package cl.app.seguridad.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.TopBox
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryPage(
    modifier: Modifier = Modifier,
    viewModel: AlertHistoryViewModel = viewModel()
) {
    val alerts by viewModel.alerts.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            TopBox(titulo = "Historial de Alertas")
        }

        if (alerts.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay alertas registradas",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            }
        } else {
            items(alerts.sortedByDescending { it.timestamp }) { alert ->
                AlertHistoryCard(alert)
            }
        }
    }
}


private fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}