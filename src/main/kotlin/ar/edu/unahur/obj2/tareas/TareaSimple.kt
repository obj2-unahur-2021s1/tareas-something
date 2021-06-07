package ar.edu.unahur.obj2.tareas

interface Tarea {
    val responsable: Empleado
    val empleados: List<Empleado>
    fun nominaDeEmpleados() = (empleados + responsable).toSet()
    fun horasNecesariasParaFinalizar(): Int
    fun costo(): Int
}

class TareaSimple(val horasEstimadas: Int, override val responsable: Empleado, override val empleados: List<Empleado>,
                  val costoDeInfraestructura: Int): Tarea {

    override fun horasNecesariasParaFinalizar() = horasEstimadas / empleados.size

    override fun costo() = horasNecesariasParaFinalizar() * sueldoPorHoraDeCadaEmpleado() +
            horasEstimadas * responsable.dineroPorHoraTrabajada + costoDeInfraestructura

    fun sueldoPorHoraDeCadaEmpleado() = empleados.sumBy { it.dineroPorHoraTrabajada }
}

class TareaDeIntegracion(override val responsable: Empleado, val tareas: List<TareaSimple>): Tarea {
    override val empleados = tareas.flatMap { it.nominaDeEmpleados() }

    override fun horasNecesariasParaFinalizar(): Int {
        val sumaSubtareas = tareas.sumBy { it.horasNecesariasParaFinalizar() }
        return sumaSubtareas + (sumaSubtareas / 8)
    }

    override fun costo() = (tareas.sumBy { it.costo() } * 1.03).toInt()
}

class Empleado(val dineroPorHoraTrabajada: Int)