package com.example.fitnessapp.utils

import com.example.fitnessapp.entities.EstadoRutina
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import java.text.SimpleDateFormat
import java.util.*

class SemanaUtils {


    companion object {


        val dateFormat = SimpleDateFormat("dd-MMMM-yyyy ")
        var dateFormayDay: SimpleDateFormat = SimpleDateFormat("EEEE")


        fun getFechaActualFormateada(): String {

            return dateFormat.format(Date())
        }

        fun getFechaSemanaFormateada(semana: Semana): String {

            return dateFormat.format(Date(semana.fechaInicio.time))
        }

        /**Retorna -1 si la semana esta completa
         * o el numero de posicion de la rutina si la semana esta incompleta
         *
         */
        fun obtenerRutinaPorHacer(semana: Semana): Int {

            var contador: Int = 0
            var posicion: Int = -1
            var semanaIncompleta: Boolean = false

            while (contador < semana.rutinas.size && !semanaIncompleta) {

                if (isRutinaDescansoOrCompletadaOrAusente(semana.rutinas[contador])) {
                    contador++
                } else {
                    semanaIncompleta = true
                    posicion = contador
                }
            }
            return posicion
        }

        fun isRutinaDescansoOrCompletadaOrAusente(rutina: Rutina): Boolean {
            return rutina.estado ==EstadoRutina.ES_DESCANSO.value || rutina.estado ==EstadoRutina.COMPLETADA.value
                    || rutina.estado ==EstadoRutina.AUSENTE.value
        }

        /**  Sunday is 1, Saturady is 7. */
        fun obtenerDia(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.DAY_OF_WEEK)
        }

        fun validarSemana(semana: Semana) {

            val fechaActual = Date()
            val fechaSemana = Date(semana.fechaInicio.time)
            val c = Calendar.getInstance()
            c.time = fechaSemana


            var contador: Int = 0
            var laFechaEsMayor = false


            while (contador < semana.rutinas.size && !laFechaEsMayor) {
                var fechaRutina = c.time

                if (fechaActual.after(fechaRutina)) {
                    validarAusente(semana.rutinas[contador])

                } else {
                    laFechaEsMayor = true
                }
                c.add(Calendar.DAY_OF_YEAR, 1)
            }


        }

        fun validarAusente(rutina: Rutina) {

            if (rutina.estado==EstadoRutina.ES_DESCANSO.value || rutina.estado==EstadoRutina.COMPLETADA.value) {

            } else {
                rutina.estado = EstadoRutina.AUSENTE.value
            }


        }

        fun obtenerFechaDiaLunesDeLaSemana(date: Date): Date {

            val c = Calendar.getInstance()
            c.time = date

            while (c.get(Calendar.DAY_OF_WEEK) != 2) {
                c.add(Calendar.DAY_OF_YEAR, -1)
            }

            return c.time
        }
    }
}

fun main(args: Array<String>) {


    println(EstadoRutina.AUSENTE.value)
}
