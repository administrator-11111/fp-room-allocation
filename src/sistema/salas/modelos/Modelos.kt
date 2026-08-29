package sistema.salas.modelos

data class Sala(
    val id: Int,
    val capacidad: Int,
    val equipamiento: List<String> = emptyList()
)

data class FranjaHoraria(
    val inicio: Int,
    val fin: Int
)

data class Solicitud(
    val id: Int,
    val franja: FranjaHoraria,
    val asistentes: Int,
    val equipamientoRequerido: List<String> = emptyList()
)

data class Asignacion(
    val solicitud: Solicitud,
    val sala: Sala
)

data class Rechazo(
    val solicitud: Solicitud,
    val motivo: String
)

data class EstadoProcesamiento(
    val asignaciones: List<Asignacion> = emptyList(),
    val rechazos: List<Rechazo> = emptyList(),
)