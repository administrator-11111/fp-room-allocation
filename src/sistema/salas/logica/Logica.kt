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

        // Determina la primera sala del catálogo que satisface las tres restricciones
        val salaApta = salas.firstOrNull { sala ->
            restricciones.all { restriccion -> restriccion(sala, solicitud, estadoActual) }
        }

        if (salaApta != null) {
            estadoActual.copy(
                asignaciones = estadoActual.asignaciones + Asignacion(solicitud, salaApta)
            )
        } else {
            val motivo = motivoRechazo(salas, solicitud)
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
    return "Salas aptas ya están ocupadas en la franja ${solicitud.franja.inicio}-${solicitud.franja.fin}."
}