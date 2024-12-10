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
        val city: String?,
        val latitude: Double,
        val longitude: Double
    )

    @SuppressLint("MissingPermission")
    suspend fun getDetailedLocation(): DetailedLocation? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                        val address = addresses?.firstOrNull()

                        if (address != null) {
                            val detailedLocation = DetailedLocation(
                                address = address.getAddressLine(0),
                                street = address.thoroughfare,
                                number = address.subThoroughfare,
                                commune = address.subLocality,
                                city = address.locality,
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                            continuation.resume(detailedLocation)
                        } else {
                            continuation.resume(null)
                        }
                    } else {
                        continuation.resume(null)
                    }
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }
}