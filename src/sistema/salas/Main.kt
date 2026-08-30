package sistema.salas

import sistema.salas.modelos.*
import sistema.salas.logica.*

fun main() {
    println("Bienvenido al sistema de asignamiento de Salas")
    // Creamos el catálogo fijo de salas
    val catalogoSalas = listOf(
        Sala(id = 1, capacidad = 10, equipamiento = listOf("pizarra")),
        Sala(id = 2, capacidad = 15, equipamiento = listOf("pizarra", "proyector", "aire_acondicionado")),
        Sala(id = 3, capacidad = 20, equipamiento = listOf("pizarra", "videoconferencia")),
        Sala(id = 4, capacidad = 30, equipamiento = listOf("proyector", "pizarra", "aire_acondicionado")),
        Sala(id = 5, capacidad = 50, equipamiento = listOf("proyector", "sonido", "aire_acondicionado")),
        Sala(id = 6, capacidad = 100, equipamiento = listOf("proyector", "sonido", "grabacion", "aire_acondicionado", "streaming")),
        Sala(id = 7, capacidad = 150, equipamiento = listOf("proyector", "sonido", "videoconferencia", "grabacion", "aire_acondicionado", "streaming")),
        Sala(id = 8, capacidad = 12, equipamiento = listOf("pizarra", "aire_acondicionado")),
        Sala(id = 9, capacidad = 25, equipamiento = listOf("proyector", "pizarra")),
        Sala(id = 10, capacidad = 40, equipamiento = listOf("proyector", "videoconferencia", "sonido")),
        Sala(id = 11, capacidad = 60, equipamiento = listOf("proyector", "sonido", "aire_acondicionado", "pizarra")),
        Sala(id = 12, capacidad = 80, equipamiento = listOf("proyector", "sonido", "videoconferencia", "aire_acondicionado")),
        Sala(id = 13, capacidad = 120, equipamiento = listOf("proyector", "sonido", "grabacion", "aire_acondicionado")),
        Sala(id = 14, capacidad = 15, equipamiento = listOf("pizarra", "videoconferencia", "aire_acondicionado")),
        Sala(id = 15, capacidad = 20, equipamiento = listOf("proyector", "aire_acondicionado")),
        Sala(id = 16, capacidad = 35, equipamiento = listOf("proyector", "pizarra", "sonido")),
        Sala(id = 17, capacidad = 45, equipamiento = listOf("proyector", "videoconferencia", "sonido", "aire_acondicionado")),
        Sala(id = 18, capacidad = 70, equipamiento = listOf("proyector", "sonido", "grabacion", "aire_acondicionado")),
        Sala(id = 19, capacidad = 90, equipamiento = listOf("proyector", "sonido", "streaming", "aire_acondicionado")),
        Sala(id = 20, capacidad = 140, equipamiento = listOf("proyector", "sonido", "videoconferencia", "grabacion", "aire_acondicionado", "streaming"))
    )

    val flujoSolicitudes = listOf(
        Solicitud(id = 101, franja = FranjaHoraria(800, 930), asistentes = 12, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 102, franja = FranjaHoraria(830, 1000), asistentes = 45, equipamientoRequerido = listOf("proyector", "sonido")),
        Solicitud(id = 103, franja = FranjaHoraria(900, 1100), asistentes = 120, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado")),
        Solicitud(id = 104, franja = FranjaHoraria(930, 1030), asistentes = 8, equipamientoRequerido = listOf("videoconferencia")),
        Solicitud(id = 105, franja = FranjaHoraria(1000, 1130), asistentes = 30, equipamientoRequerido = listOf("proyector", "pizarra")),
        Solicitud(id = 106, franja = FranjaHoraria(1030, 1200), asistentes = 80, equipamientoRequerido = listOf("proyector", "sonido", "grabacion")),
        Solicitud(id = 107, franja = FranjaHoraria(1100, 1300), asistentes = 15, equipamientoRequerido = listOf("pizarra", "aire_acondicionado")),
        Solicitud(id = 108, franja = FranjaHoraria(1200, 1330), asistentes = 50, equipamientoRequerido = listOf("proyector", "sonido", "videoconferencia")),
        Solicitud(id = 109, franja = FranjaHoraria(1300, 1430), asistentes = 10, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 110, franja = FranjaHoraria(1400, 1600), asistentes = 135, equipamientoRequerido = listOf("proyector", "sonido", "streaming", "aire_acondicionado")),
        Solicitud(id = 111, franja = FranjaHoraria(1430, 1530), asistentes = 20, equipamientoRequerido = listOf("proyector")),
        Solicitud(id = 112, franja = FranjaHoraria(1500, 1700), asistentes = 65, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado")),
        Solicitud(id = 113, franja = FranjaHoraria(1530, 1630), asistentes = 12, equipamientoRequerido = listOf("videoconferencia", "aire_acondicionado")),
        Solicitud(id = 114, franja = FranjaHoraria(1600, 1800), asistentes = 90, equipamientoRequerido = listOf("proyector", "sonido", "grabacion")),
        Solicitud(id = 115, franja = FranjaHoraria(1700, 1900), asistentes = 145, equipamientoRequerido = listOf("proyector", "sonido", "streaming", "aire_acondicionado")),
        Solicitud(id = 116, franja = FranjaHoraria(800, 1000), asistentes = 25, equipamientoRequerido = listOf("proyector", "pizarra")),
        Solicitud(id = 117, franja = FranjaHoraria(900, 1030), asistentes = 5, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 118, franja = FranjaHoraria(1000, 1200), asistentes = 110, equipamientoRequerido = listOf("proyector", "sonido", "videoconferencia")),
        Solicitud(id = 119, franja = FranjaHoraria(1130, 1300), asistentes = 35, equipamientoRequerido = listOf("proyector", "aire_acondicionado")),
        Solicitud(id = 120, franja = FranjaHoraria(1400, 1530), asistentes = 18, equipamientoRequerido = listOf("pizarra", "videoconferencia")),
        Solicitud(id = 121, franja = FranjaHoraria(1500, 1800), asistentes = 75, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado")),
        Solicitud(id = 122, franja = FranjaHoraria(1630, 1830), asistentes = 40, equipamientoRequerido = listOf("proyector", "sonido")),
        Solicitud(id = 123, franja = FranjaHoraria(830, 930), asistentes = 10, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 124, franja = FranjaHoraria(930, 1130), asistentes = 130, equipamientoRequerido = listOf("proyector", "sonido", "grabacion", "aire_acondicionado")),
        Solicitud(id = 125, franja = FranjaHoraria(1030, 1230), asistentes = 55, equipamientoRequerido = listOf("proyector", "sonido", "videoconferencia")),
        Solicitud(id = 126, franja = FranjaHoraria(1200, 1400), asistentes = 22, equipamientoRequerido = listOf("proyector", "pizarra")),
        Solicitud(id = 127, franja = FranjaHoraria(1330, 1500), asistentes = 8, equipamientoRequerido = listOf("videoconferencia", "aire_acondicionado")),
        Solicitud(id = 128, franja = FranjaHoraria(1430, 1630), asistentes = 85, equipamientoRequerido = listOf("proyector", "sonido", "streaming")),
        Solicitud(id = 129, franja = FranjaHoraria(1530, 1730), asistentes = 15, equipamientoRequerido = listOf("pizarra", "aire_acondicionado")),
        Solicitud(id = 130, franja = FranjaHoraria(1700, 1830), asistentes = 45, equipamientoRequerido = listOf("proyector", "sonido")),
        Solicitud(id = 131, franja = FranjaHoraria(800, 1200), asistentes = 140, equipamientoRequerido = listOf("proyector", "sonido", "videoconferencia", "aire_acondicionado")),
        Solicitud(id = 132, franja = FranjaHoraria(900, 1000), asistentes = 12, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 133, franja = FranjaHoraria(1000, 1100), asistentes = 28, equipamientoRequerido = listOf("proyector")),
        Solicitud(id = 134, franja = FranjaHoraria(1100, 1300), asistentes = 60, equipamientoRequerido = listOf("proyector", "sonido", "grabacion")),
        Solicitud(id = 135, franja = FranjaHoraria(1300, 1400), asistentes = 6, equipamientoRequerido = listOf("videoconferencia")),
        Solicitud(id = 136, franja = FranjaHoraria(1400, 1700), asistentes = 115, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado", "streaming")),
        Solicitud(id = 137, franja = FranjaHoraria(1500, 1600), asistentes = 20, equipamientoRequerido = listOf("pizarra", "proyector")),
        Solicitud(id = 138, franja = FranjaHoraria(1600, 1730), asistentes = 35, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado")),
        Solicitud(id = 139, franja = FranjaHoraria(1730, 1930), asistentes = 80, equipamientoRequerido = listOf("proyector", "sonido", "videoconferencia")),
        Solicitud(id = 140, franja = FranjaHoraria(1800, 1900), asistentes = 15, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 141, franja = FranjaHoraria(830, 1130), asistentes = 95, equipamientoRequerido = listOf("proyector", "sonido", "grabacion", "aire_acondicionado")),
        Solicitud(id = 142, franja = FranjaHoraria(930, 1100), asistentes = 14, equipamientoRequerido = listOf("videoconferencia", "aire_acondicionado")),
        Solicitud(id = 143, franja = FranjaHoraria(1130, 1330), asistentes = 50, equipamientoRequerido = listOf("proyector", "sonido")),
        Solicitud(id = 144, franja = FranjaHoraria(1230, 1430), asistentes = 10, equipamientoRequerido = listOf("pizarra")),
        Solicitud(id = 145, franja = FranjaHoraria(1430, 1530), asistentes = 25, equipamientoRequerido = listOf("proyector", "pizarra")),
        Solicitud(id = 146, franja = FranjaHoraria(1530, 1830), asistentes = 125, equipamientoRequerido = listOf("proyector", "sonido", "videoconferencia", "streaming")),
        Solicitud(id = 147, franja = FranjaHoraria(1630, 1730), asistentes = 8, equipamientoRequerido = listOf("videoconferencia")),
        Solicitud(id = 148, franja = FranjaHoraria(1700, 1900), asistentes = 65, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado")),
        Solicitud(id = 149, franja = FranjaHoraria(1830, 2030), asistentes = 40, equipamientoRequerido = listOf("proyector", "sonido")),
        Solicitud(id = 150, franja = FranjaHoraria(1900, 2100), asistentes = 110, equipamientoRequerido = listOf("proyector", "sonido", "aire_acondicionado"))
    )

    // Asignamos las tres restricciones en una lista inmutable
    val restriccionesAsignacion: List<(Sala, Solicitud, EstadoProcesamiento) -> Boolean> = listOf(
        restriccionCapacidad,
        restriccionEquipamiento,
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