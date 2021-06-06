package ar.edu.unahur.obj2.tareas


class TareaSimple(val horasEstimadas: Int, val responsable: Empleado, val empleados: List<Empleado>,
                  val costoDeInfraestructura: Int) {

    fun nominaDeEmpleados() = this.empleados + this.responsable

    fun horasNecesariasParaFinalizar() = horasEstimadas / empleados.size

    fun costo() = horasNecesariasParaFinalizar() * sueldoPorHoraDeCadaEmpleado() +
            horasEstimadas * responsable.dineroPorHoraTrabajada + costoDeInfraestructura

    fun sueldoPorHoraDeCadaEmpleado() = empleados.sumBy { it.dineroPorHoraTrabajada }
}

class TareaDeIntegracion(val responsable: Empleado, val tareas: List<TareaSimple>) {
    fun horasNecesariasParaFinalizar(): Int {
        val sumaSubtareas = tareas.sumBy { it.horasNecesariasParaFinalizar() }
        return sumaSubtareas + (sumaSubtareas / 8)
    }

    fun costo() = tareas.sumBy { it.costo() } * 1.03

    fun nominaDeEmpleados() = (tareas.flatMap { it.nominaDeEmpleados() } + this.responsable).toSet()
}
