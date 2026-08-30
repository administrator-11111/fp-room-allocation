package sistema.salas.logica
import sistema.salas.modelos.*

/* Funciones de orden superior:
 * En lugar de agrupar todas las validaciones en un if dentro de un ciclo,
 * extraemos las tres restricciones del caso de estudio en funciones independientes.
 * Así, logramos una "separación de preocupaciones" mediante funciones de orden superior (Van Roy, 2009),
 * separando el "cómo iteramos" del "qué evaluamos", haciendo el código modular.
 */

val seSuperponeCon: (FranjaHoraria, FranjaHoraria) -> Boolean = { f1, f2 ->
    f1.inicio < f2.fin && f1.fin > f2.inicio
}

/*
 * FUNCIONES DE ORDEN SUPERIOR (SEPARACIÓN DE PREOCUPACIONES)
 * Extraemos las tres restricciones del caso de estudio en funciones independientes.
 * Así logramos una "separación de preocupaciones" mediante funciones de orden superior
 * (Van Roy, 2009, p. 25). Separamos el "cómo iteramos" del "qué evaluamos".
 */

// Devuelve true si la sala tiene capacidad mayor o igual a los asistentes
val restriccionCapacidad: (Sala, Solicitud, EstadoProcesamiento) -> Boolean = { sala, solicitud, _ ->
    sala.capacidad >= solicitud.asistentes
}

// Devuelve true si la sala contiene todo el equipamiento requerido
val restriccionEquipamiento: (Sala, Solicitud, EstadoProcesamiento) -> Boolean = { sala, solicitud, _ ->
    sala.equipamiento.containsAll(solicitud.equipamientoRequerido)
}

// Devuelve true si la sala NO tiene asignaciones que choquen en horario
val restriccionDisponibilidad: (Sala, Solicitud, EstadoProcesamiento) -> Boolean = { sala, solicitud, estado ->
    estado.asignaciones.none { asignacion ->
        asignacion.sala.id == sala.id && seSuperponeCon(solicitud.franja, asignacion.solicitud.franja)
    }
}

/*
 * CLAUSURAS CON ALCANCE LÉXICO (CLOSURES)
 * Procesa el flujo secuencial de solicitudes. El bloque dentro de 'fold'
 * actúa como una clausura léxica (Van Roy, 2009, p. 24) que captura 'solicitud'
 * y 'estadoActual' de su entorno sin recurrir a variables mutables.
 */

// Devuelve un nuevo objeto EstadoProcesamiento con las listas finales.
fun procesarSolicitudes(
    salas: List<Sala>,
    solicitudes: List<Solicitud>,
    restricciones: List<(Sala, Solicitud, EstadoProcesamiento) -> Boolean>
): EstadoProcesamiento {

    return solicitudes.fold(EstadoProcesamiento()) { estadoActual, solicitud ->

        println("\uD83D\uDCDD Procesando Solicitud N° ${solicitud.id} [${solicitud.asistentes} personas | ${solicitud.equipamientoRequerido.size} equipos | ${formatearHora(solicitud.franja.inicio)} hasta ${formatearHora(solicitud.franja.fin)}]")

        // Determina la primera sala del catálogo que satisface las tres restricciones
        val salaApta = salas.firstOrNull { sala ->
            restricciones.all { restriccion -> restriccion(sala, solicitud, estadoActual) }
        }

        if (salaApta != null) {
            println(" 🟢 Aprobada, se asignó la sala N°${salaApta.id}\n")
            estadoActual.copy(
                asignaciones = estadoActual.asignaciones + Asignacion(solicitud, salaApta)
            )
        } else {
            val motivo = motivoRechazo(salas, solicitud)
            println(" ❌ Rechazada por ${motivo}\n")
            estadoActual.copy(
                rechazos = estadoActual.rechazos + Rechazo(solicitud, motivo)
            )
        }
    }
}

// Devuelve un string con el motivo del rechazo deduciendo el fallo
fun motivoRechazo(salas: List<Sala>, solicitud: Solicitud): String {
    if (salas.none { it.capacidad >= solicitud.asistentes }) {
        return "Capacidad insuficiente en el catálogo (${solicitud.asistentes} requeridos)."
    }
    if (salas.none { it.equipamiento.containsAll(solicitud.equipamientoRequerido) }) {
        return "Equipamiento no disponible: ${solicitud.equipamientoRequerido}."
    }
    return "Salas aptas ya están ocupadas en el horario solicitado"
}

// Convierte hora en formato militar (INT) a un formato estandar (retorna string)
fun formatearHora(horaInt: Int): String {
    val horasMilitares = horaInt / 100
    val minutos = horaInt % 100

    if (horaInt !in 0..2400 || minutos > 59 || (horasMilitares == 24 && minutos > 0)) {
        return "Hora inválida"
    }

    val periodo = if (horasMilitares in 12..23) "p.m" else "a.m"

    val horasNormales = when {
        horasMilitares == 0 || horasMilitares == 24 -> 12
        horasMilitares > 12 -> horasMilitares - 12
        else -> horasMilitares
    }

    val minutosTexto = minutos.toString().padStart(2, '0')

    return "$horasNormales:$minutosTexto $periodo"
}

fun informeFinal(informe: EstadoProcesamiento) {
    println("--------------------------------------------------------------------------------")
    println("┌────────────────────────────────────────┐")
    println("│  🖨️  REPORTE DE ASIGNACIONES FINAL     │")
    println("└────────────────────────────────────────┘")
    println("✅ Asignaciones aceptadas (Total: ${informe.asignaciones.size})")
    informe.asignaciones.forEach { asignacion ->
        println("  📥 Solicitud ${asignacion.solicitud.id} -> Sala N°${asignacion.sala.id} (Horario: ${formatearHora(asignacion.solicitud.franja.inicio)} a ${formatearHora(asignacion.solicitud.franja.fin)})")
    }

    println("\n⛔ Asignaciones rechazadas (Total: ${informe.rechazos.size})")
    informe.rechazos.forEach { rechazo ->
        println("  📤 Solicitud ${rechazo.solicitud.id} -> Motivo: ${rechazo.motivo}")
    }
    println("--------------------------------------------------------------------------------")
}