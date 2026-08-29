package sistema.salas.modelos

// Código sugerido por Lucas (27-08)
data class Sala(
    val id: Int,
    val capacidad: Int,
    val equipamiento: List<String> = listOf()
)


data class FranjaHoraria(
    val inicio: Int,
    val fin: Int,
)

data class Solicitud(
    val soliid: Int,
    val franja: FranjaHoraria,
    val asistentes: Int,
    val equipamientoRequerido: List<String> = listOf()
)

data class Asignacion(
    val solicitudId: Int,
    val salaId: String,
    val franja: FranjaHoraria
)

data class Rechazo(
    val solicitudId: String,
    val motivo: String
)

data class EstadoProcesamiento(
    val asignaciones: List<Asignacion> = listOf(),
    val rechazos: List<Rechazo> = listOf()
)