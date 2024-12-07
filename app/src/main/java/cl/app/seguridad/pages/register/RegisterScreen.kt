package cl.app.seguridad.pages.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.CaptureActivity
import androidx.compose.ui.tooling.preview.Preview
import cl.app.seguridad.CaptureActivityPortait


@Preview(showBackground = true)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RegisterScreen() {
    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA
        )
    )

    LaunchedEffect(key1 = Unit) {
        permissions.launchMultiplePermissionRequest()
    }

    var listaStrings by remember { mutableStateOf(emptyList<String>()) }
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            val aux = result.contents ?: ""
            if (aux.isNotEmpty()) {
                val lista = listaStrings.toMutableList()
                lista.add(aux)
                listaStrings = lista
            }

        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Button(
                    modifier = Modifier
                        .wrapContentSize(),
                    onClick = {
                        val scanOptions = ScanOptions()
                        scanOptions.setBeepEnabled(true)
                        scanOptions.setCaptureActivity(CaptureActivityPortait::class.java)
                        scanOptions.setOrientationLocked(false)
                        scanLauncher.launch(scanOptions)
                    }
                ) {
                    Text(text = "Escanear QR")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "DATOS CEDULA")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    text = "${listaStrings.size} Registros"
                )
            }

            LazyColumn(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                items(listaStrings) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(alignment = Alignment.CenterVertically)
                                    .weight(2f),
                                text = item
                            )

                            IconButton(
                                onClick = {
                                    val lista = listaStrings.toMutableList()
                                    lista.remove(item)
                                    listaStrings = lista
                                }
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(alignment = Alignment.CenterVertically)
                                        .weight(0.3f),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "borrar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}