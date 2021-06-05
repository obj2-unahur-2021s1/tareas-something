package ar.edu.unahur.obj2.tareas

class Tarea(val horasEstimadas: Int, val responsable: Empleado, val empleados: List<Empleado>,
            val costoDeInfraestructura: Int) {

    fun nominaDeEmpleados() = this.empleados + this.responsable

// redondea para abajo en Int
    fun horasNecesariasParaFinalizar() = horasEstimadas / empleados.size

    fun costo() = horasNecesariasParaFinalizar() * sueldoPorHoraDeCadaEmpleado() +
            horasEstimadas * responsable.dineroPorHoraTrabajada + costoDeInfraestructura

    fun sueldoPorHoraDeCadaEmpleado() = empleados.sumBy { it.dineroPorHoraTrabajada }
}

class TareaDeIntegracion(val responsable: Empleado, val tareas: List<Tarea>) {
    fun horasNecesariasParaFinalizar(): Int {
        val sumaSubtareas = tareas.sumBy { it.horasNecesariasParaFinalizar() }
        return sumaSubtareas + (sumaSubtareas / 8)
    }

    fun costo() = tareas.sumBy { it.costo() } * 1.03

    fun nominaDeEmpleados() = tareas.flatMap { it.nominaDeEmpleados() } + this.responsable
}
