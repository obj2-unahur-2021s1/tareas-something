package ar.edu.unahur.obj2.tareas

class Tarea(val horasEstimadas: Int, val responsable: Empleado, val empleados: MutableList<Empleado>,
            val costoDeInfraestructura: Int) {

    fun nominaDeEmpleados(): List<Empleado> {
        val nomina = empleados
        nomina.add(responsable)
        return nomina
    }

    fun horasNecesariasParaFinalizar() = horasEstimadas / empleados.size

    fun costo() = horasNecesariasParaFinalizar() * sueldoPorHoraDeCadaEmpleado() +
            horasEstimadas * responsable.dineroPorHoraTrabajada + costoDeInfraestructura

    fun sueldoPorHoraDeCadaEmpleado() = empleados.sumBy { it.dineroPorHoraTrabajada }

    
}