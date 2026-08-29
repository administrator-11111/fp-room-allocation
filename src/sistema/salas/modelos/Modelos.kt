package sistema.salas.modelos

/*
 * REGISTRO (Record) Y DATOS INMUTABLES
 * En lugar de usar objetos tradicionales, usamos 'data class' con valores inmutables (val).
 * Según Van Roy (2009, p. 23), los registros son el bloque de construcción
 * fundamental para las estructuras simbólicas, asegurando que no tengamos
 * errores de estado compartido ni efectos secundarios.
 */

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
    val rechazos: List<Rechazo> = emptyList()
)