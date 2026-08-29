// Código sugerido por Lucas (27-08)

package sistema.salas.logica

import sistema.salas.modelos.sistema.salas.modelos.Sala

//funcion que muestra las salas
fun mostrarSalas(catalogo:List<Sala>){
    println("Este es el catalogo de salas disponibles ")
    catalogo.forEach{ sala -> println(sala)}
}
//funcion que verifica que la solicitud cumpla con la capacidad de la sala que estan solicitando
fun verificaCapacidad( capacidad: Int, asistentes:Int):Boolean = asistentes <= capacidad

//funcion que verifica que la solicitud cumpla con el equipamento solicitado
fun verificaEquipamiento(equipamiento: List<String>, equipamientoRequerido: List<String>): Boolean {
    if(equipamiento.containsAll(equipamientoRequerido)){
        return true
    }
    else return false
}
//funcion que verifica que la solicitud sea en un rango donde no haya una sala usada
fun verificaHorario( inicio:Int,fin:Int, soliid:Int) {
    if(soliid== 1){

    }
}