package sistema.salas

import sistema.salas.modelos.*
import sistema.salas.logica.*

fun main() {
    println("Bienvenido al sistema de asignamiento de Salas")
    // Creamos el catálogo fijo de salas
    val catalogoSalas = listOf(
        Sala(id = 199, capacidad = 8, equipamiento = listOf("pizarra")),
        Sala(id = 299, capacidad = 10, equipamiento = listOf("proyector", "pizarra")),
        Sala(id = 399, capacidad = 12, equipamiento = listOf("pizarra", "televisor"))
    )

    val flujoSolicitudes = listOf(
        Solicitud(id = 1, FranjaHoraria(830, 1000), asistentes = 4, equipamientoRequerido = listOf("pizarra")),
        Solicitud(
            id = 2,
            FranjaHoraria(1030, 1200),
            asistentes = 8,
            equipamientoRequerido = listOf("proyector", "pizarra")
        ),
        Solicitud(
            id = 3,
            FranjaHoraria(1400, 1530),
            asistentes = 10,
            equipamientoRequerido = listOf("pizarra", "televisor")
        ),
        Solicitud(
            id = 4,
            FranjaHoraria(1600, 1800),
            asistentes = 12,
            equipamientoRequerido = listOf("proyector", "pizarra", "aire_acondicionado")
        ),
        Solicitud(
            id = 5,
            FranjaHoraria(1830, 2030),
            asistentes = 5,
            equipamientoRequerido = listOf("proyector", "pizarra", "sonido")
        ),
        Solicitud(
            id = 6,
            FranjaHoraria(900, 1130),
            asistentes = 15,
            equipamientoRequerido = listOf("proyector", "pizarra", "videoconferencia", "sonido")
        ),
        Solicitud(
            id = 7,
            FranjaHoraria(1200, 1330),
            asistentes = 6,
            equipamientoRequerido = listOf("proyector", "pizarra", "grabacion")
        ),
        Solicitud(
            id = 8,
            FranjaHoraria(1900, 2100),
            asistentes = 10,
            equipamientoRequerido = listOf("proyector", "pizarra", "videoconferencia", "aire_acondicionado")
        ),
        Solicitud(id = 9, FranjaHoraria(900, 1030), asistentes = 5, equipamientoRequerido = listOf("pizarra")),
        Solicitud(
            id = 10,
            FranjaHoraria(1430, 1600),
            asistentes = 7,
            equipamientoRequerido = listOf("pizarra", "televisor")
        ),
        Solicitud(
            id = 11,
            FranjaHoraria(930, 1100),
            asistentes = 18,
            equipamientoRequerido = listOf("proyector", "pizarra", "videoconferencia", "sonido")
        ),
        Solicitud(id = 13, FranjaHoraria(830, 1000), asistentes = 30, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 14, FranjaHoraria(1400, 1600), asistentes = 50, equipamientoRequerido = listOf("proyector")),
        Solicitud(id = 15, FranjaHoraria(1000, 1200), asistentes = 5, equipamientoRequerido = listOf("laboratorio")),
        Solicitud(
            id = 16,
            FranjaHoraria(1600, 1800),
            asistentes = 8,
            equipamientoRequerido = listOf("pizarra", "impresora_3d")
        ),
        Solicitud(id = 17, FranjaHoraria(1100, 1300), asistentes = 20, equipamientoRequerido = listOf("streaming")),
        Solicitud(
            id = 18,
            FranjaHoraria(830, 1030),
            asistentes = 25,
            equipamientoRequerido = listOf("aire_acondicionado")
        ),
        Solicitud(
            id = 19,
            FranjaHoraria(1700, 1830),
            asistentes = 4,
            equipamientoRequerido = listOf("pizarra", "televisor")
        ),
        Solicitud(
            id = 20,
            FranjaHoraria(1200, 1400),
            asistentes = 12,
            equipamientoRequerido = listOf("proyector", "pizarra", "sonido")
        )
    )

    // Asignamos las tres restricciones en una lista inmutable
    val restriccionesAsignacion: List<(Sala, Solicitud, EstadoProcesamiento) -> Boolean> = listOf(
        restriccionCapacidad,
        restriccionCapacidad,
        restriccionDisponibilidad
    )

    val resultado = procesarSolicitudes(catalogoSalas, flujoSolicitudes, restriccionesAsignacion)

    println("[>] Asignaciones aceptadas:")
    resultado.asignaciones.forEach { asignacion ->
        println("* Solicitud ${asignacion.solicitud.id} -> Sala ${asignacion.sala.id} (Horario: ${asignacion.solicitud.franja.inicio} a ${asignacion.solicitud.franja.fin})")
    }

    println("[X] Asignaciones rechazadas:")
    resultado.rechazos.forEach { rechazo ->
        println("* Solicitud ${rechazo.solicitud.id} -> Rechazada: ${rechazo.motivo}")
    }
}