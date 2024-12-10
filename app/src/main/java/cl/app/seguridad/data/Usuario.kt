package cl.app.seguridad.data

data class Usuario (
    val nombre: String,
    val apellido: String,
    val email: String,
    val rut: String,
    val password: String,
    val passwordConfirmation: String,
    val estadoCuenta: Boolean,
    val fechaRegistro: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val verificado: Boolean,
    val tokenVerificacion: String
)