import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import kotlin.coroutines.resume

class LocationManager(private val context: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    data class DetailedLocation(
        val address: String,
        val street: String?,
        val number: String?,
        val commune: String?,
        val city: String?
    )

    @SuppressLint("MissingPermission")
    suspend fun getDetailedLocation(): DetailedLocation? {
        return suspendCancellableCoroutine { continuation ->
            try {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            val geocoder = Geocoder(context, Locale.getDefault())
                            try {
                                val addresses = geocoder.getFromLocation(
                                    location.latitude,
                                    location.longitude,
                                    1
                                )
                                val address = addresses?.firstOrNull()
                                if (address != null) {
                                    val detailedLocation = DetailedLocation(
                                        address = address.getAddressLine(0) ?: "",
                                        street = address.thoroughfare,
                                        number = address.subThoroughfare,
                                        commune = address.subLocality,
                                        city = address.locality
                                    )
                                    println("Ubicación obtenida exitosamente: $detailedLocation")
                                    continuation.resume(detailedLocation)
                                } else {
                                    println("No se pudo obtener la dirección")
                                    continuation.resume(null)
                                }
                            } catch (e: Exception) {
                                println("Error al obtener la dirección: ${e.message}")
                                continuation.resume(null)
                            }
                        } else {
                            println("Location es null")
                            continuation.resume(null)
                        }
                    }
                    .addOnFailureListener { e ->
                        println("Error al obtener ubicación: ${e.message}")
                        continuation.resume(null)
                    }
            } catch (e: Exception) {
                println("Error general: ${e.message}")
                continuation.resume(null)
            }
        }
    }
}