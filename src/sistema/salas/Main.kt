package sistema.salas

import sistema.salas.logica.*
import sistema.salas.modelos.sistema.salas.modelos.FranjaHoraria
import sistema.salas.modelos.sistema.salas.modelos.Sala
import sistema.salas.modelos.sistema.salas.modelos.Solicitud

// Código sugerido por Lucas (27-08)

fun main() {
    println("Bienvenido al sistema de asignamiento de Salas")
    val catalogo = listOf(
        Sala(199, capacidad = 8, equipamiento = listOf("pizarra")),
        Sala(299, capacidad = 10, equipamiento = listOf("proyector", "pizarra")),
        Sala(399, capacidad = 12, equipamiento = listOf("pizarra", "televisor")),
        Sala(499, capacidad = 15, equipamiento = listOf("proyector", "pizarra", "aire_acondicionado")),
        Sala(599, capacidad = 20, equipamiento = listOf("proyector", "pizarra", "videoconferencia")),
        Sala(
            699, capacidad


            = 25, equipamiento = listOf("proyector", "pizarra", "sonido")
        ),
        Sala(
            799,
            capacidad = 10,
            equipamiento = listOf("proyector", "pizarra", "videoconferencia", "aire_acondicionado")
        ),
        Sala(899, capacidad = 15, equipamiento = listOf("proyector", "pizarra", "sonido", "grabacion")),
        Sala(999, capacidad = 20, equipamiento = listOf("proyector", "pizarra", "videoconferencia", "sonido")),
        Sala(
            1099,
            capacidad = 23,
            equipamiento = listOf("proyector", "pizarra", "videoconferencia", "sonido", "aire_acondicionado")
        ),
        Sala(
            1199,
            capacidad = 6,
            equipamiento = listOf("proyector", "pizarra", "sonido", "grabacion", "aire_acondicionado")
        ),
        Sala(
            1299,
            capacidad = 20,
            equipamiento = listOf("proyector", "pizarra", "videoconferencia", "sonido", "grabacion")
        ),
        Sala(
            1399,
            capacidad = 10,
            equipamiento = listOf(
                "proyector",
                "pizarra",
                "videoconferencia",
                "sonido",
                "grabacion",
                "aire_acondicionado"
            )
        ),
        Sala(
            1499,
            capacidad = 13,
            equipamiento = listOf(
                "proyector",
                "pizarra",
                "videoconferencia",
                "sonido",
                "grabacion",
                "aire_acondicionado",
                "streaming"
            )
        ),
        Sala(1599, capacidad = 6, equipamiento = listOf("pizarra", "televisor"))
    )
    val solicitudes = listOf(

        Solicitud(1, FranjaHoraria(830, 1000), asistentes = 4, equipamientoRequerido = listOf("pizarra")),
        Solicitud(2, FranjaHoraria(1030, 1200), asistentes = 8, equipamientoRequerido = listOf("proyector", "pizarra")),
        Solicitud(
            3,
            FranjaHoraria(1400, 1530),
            asistentes = 10,
            equipamientoRequerido = listOf("pizarra", "televisor")
        ),
        Solicitud(
            4,
            FranjaHoraria(1600, 1800),
            asistentes = 12,
            equipamientoRequerido = listOf("proyector", "pizarra", "aire_acondicionado")
        ),
        Solicitud(
            5,
            FranjaHoraria(1830, 2030),
            asistentes = 5,
            equipamientoRequerido = listOf("proyector", "pizarra", "sonido")
        ),
        Solicitud(
            6,
            FranjaHoraria(900, 1130),
            asistentes = 15,
            equipamientoRequerido = listOf("proyector", "pizarra", "videoconferencia", "sonido")
        ),
        Solicitud(
            7,
            FranjaHoraria(1200, 1330),
            asistentes = 6,
            equipamientoRequerido = listOf("proyector", "pizarra", "grabacion")
        ),
        Solicitud(
            8,
            FranjaHoraria(1900, 2100),
            asistentes = 10,
            equipamientoRequerido = listOf("proyector", "pizarra", "videoconferencia", "aire_acondicionado")
        ),
        Solicitud(9, FranjaHoraria(900, 1030), asistentes = 5, equipamientoRequerido = listOf("pizarra")),
        Solicitud(
            10,
            FranjaHoraria(1430, 1600),
            asistentes = 7,
            equipamientoRequerido = listOf("pizarra", "televisor")
        ),
        Solicitud(
            11,
            FranjaHoraria(930, 1100),
            asistentes = 18,
            equipamientoRequerido = listOf("proyector", "pizarra", "videoconferencia", "sonido")
        ),
        Solicitud(13, FranjaHoraria(830, 1000), asistentes = 30, equipamientoRequerido = listOf("pizarra")),
        Solicitud(14, FranjaHoraria(1400, 1600), asistentes = 50, equipamientoRequerido = listOf("proyector")),
        Solicitud(15, FranjaHoraria(1000, 1200), asistentes = 5, equipamientoRequerido = listOf("laboratorio")),
        Solicitud(
            16,
            FranjaHoraria(1600, 1800),
            asistentes = 8,
            equipamientoRequerido = listOf("pizarra", "impresora_3d")
        ),
        Solicitud(17, FranjaHoraria(1100, 1300), asistentes = 20, equipamientoRequerido = listOf("streaming")),
        Solicitud(18, FranjaHoraria(830, 1030), asistentes = 25, equipamientoRequerido = listOf("aire_acondicionado")),
        Solicitud(
            19,
            FranjaHoraria(1700, 1830),
            asistentes = 4,
            equipamientoRequerido = listOf("pizarra", "televisor")
        ),
        Solicitud(
            20,
            FranjaHoraria(1200, 1400),
            asistentes = 12,
            equipamientoRequerido = listOf("proyector", "pizarra", "sonido")
        )
    )
    mostrarSalas(catalogo)
}