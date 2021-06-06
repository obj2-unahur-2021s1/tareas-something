package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({
  describe("Una tarea") {
    val empleado01 = Empleado(5)
    val empleado02 = Empleado(4)
    val empleado03 = Empleado(4)
    val empleado04 = Empleado(6)
    val grupo01 = listOf(empleado02, empleado03)
    val grupo02 = listOf(empleado01, empleado02)
    val tarea01 = TareaSimple(10, empleado01, grupo01 , 100 )
    val tarea02 = TareaSimple(8, empleado03, grupo02, 80)
    val integracion01 = TareaDeIntegracion(empleado04, listOf(tarea01, tarea02))

    describe("una tarea de integracion con dos subtareas") {
      describe("Horas necesarias") {
        it("horas de subtarea 1 (5hrs)") {
          tarea01.horasNecesariasParaFinalizar().shouldBe(5)
        }
        it("horas de subtarea 2 (4hr)") {
          tarea02.horasNecesariasParaFinalizar().shouldBe(4)
        }
        it("horas de integracion (10hrs)") {
          integracion01.horasNecesariasParaFinalizar().shouldBe(10)
        }
      }

      describe("Costo") {
        it("costo tarea 1 ($190)") {
          tarea01.costo().shouldBe(190)
        }
        it("costo tarea 2 ($148)") {
          tarea02.costo().shouldBe(148)
        }
        it("costo de integracion ($348)") {
          integracion01.costo().shouldBe(348)
        }
      }

      describe("Nomina de empleados") {
        it("nomina tarea 1 [emp01, emp03, emp02]") {
          tarea01.nominaDeEmpleados().shouldBe(listOf(empleado02, empleado03, empleado01))
        }
        it("nomina tarea 2 [emp01, emp02, emp03]") {
          tarea02.nominaDeEmpleados().shouldBe(listOf(empleado01, empleado02, empleado03))
        }
        it("nomina de integracion [e01,e02,e03,e04]") {
          integracion01.nominaDeEmpleados().shouldBe(
            setOf(empleado01,empleado02,empleado03,empleado04))
        }
      }
    }
  }
})
