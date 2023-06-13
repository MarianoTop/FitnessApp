package com.example.fitnessapp.utils

import com.example.fitnessapp.entities.EstadoRutina
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
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

            // Si la semana esta finalizada devuelvo -1
            if(semana.estaFinalizada){
                return posicion;
            }

            var numeroDiaABuscar= obtenerDia(Date())

            //Si por algun motivo esa rutina esta en null devuelvo -1
            if(semana.rutinas[numeroDiaABuscar]!=null){

                if(semana.rutinas[numeroDiaABuscar].estado==EstadoRutina.FALTA_COMPLETAR.value){
                    posicion=numeroDiaABuscar
                }else if(semana.rutinas[numeroDiaABuscar].estado==EstadoRutina.AUSENTE.value){
                    posicion= -2
                }else if(semana.rutinas[numeroDiaABuscar].estado==EstadoRutina.ES_DESCANSO.value){
                    posicion= -3
                }else if(semana.rutinas[numeroDiaABuscar].estado==EstadoRutina.COMPLETADA.value){
                    posicion= -4
                }

            }


            return posicion
        }

        fun isRutinaDescansoOrCompletadaOrAusente(rutina: Rutina): Boolean {
            return rutina.estado ==EstadoRutina.ES_DESCANSO.value || rutina.estado ==EstadoRutina.COMPLETADA.value
                    || rutina.estado ==EstadoRutina.AUSENTE.value
        }

        /**  Lunes es 0, Domingo es 6. */
        fun obtenerDia(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            var numero= calendar.get(Calendar.DAY_OF_WEEK)
            if(numero==1){
                numero=6
            }else{
                numero-=2
            }
            return numero
        }


        fun validarSemana(semana: Semana) {
            val fechaHoy = Date()
            val fechaActual = Calendar.getInstance()
            fechaActual.time = Date(fechaHoy.time)
            fechaActual.set(Calendar.HOUR_OF_DAY, 0)
            fechaActual.set(Calendar.MINUTE, 0)
            fechaActual.set(Calendar.SECOND, 0)
            fechaActual.set(Calendar.MILLISECOND, 0)

            val fechaSemana = Date(semana.fechaInicio.time)
            val c = Calendar.getInstance()
            c.time = fechaSemana
            c.set(Calendar.HOUR_OF_DAY, 0)
            c.set(Calendar.MINUTE, 0)
            c.set(Calendar.SECOND, 0)
            c.set(Calendar.MILLISECOND, 0)

            var contador: Int = 0
            var laFechaEsMayor = false

            while (contador < semana.rutinas.size && !laFechaEsMayor) {
                var fechaRutina = c.time
                println("FECHA RUTINA: " + fechaRutina + " - FECHA SEMANA: " + fechaSemana + " - FECHA ACTUAL: " + fechaActual.time)

                if (fechaActual.time.after(fechaRutina)) {
                    validarAusente(semana.rutinas[contador])
                } else {
                    laFechaEsMayor = true
                }
                c.add(Calendar.DAY_OF_YEAR, 1)
                contador++
            }
            if(!laFechaEsMayor) {
                semana.estaFinalizada = true
            }
        }


        fun validarAusente(rutina: Rutina) {

            if (rutina.estado!=EstadoRutina.ES_DESCANSO.value && rutina.estado!=EstadoRutina.COMPLETADA.value) {
                println("La rutina está ausente.")
                rutina.estado = EstadoRutina.AUSENTE.value
            }
        }

        fun obtenerFechaDiaLunesDeLaSemana(date: Date): Date {

            val c = Calendar.getInstance()
            c.time = date
            c.set(Calendar.HOUR_OF_DAY, 0)
            c.set(Calendar.MINUTE, 0)
            c.set(Calendar.SECOND, 0)
            c.set(Calendar.MILLISECOND, 0)

            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                c.add(Calendar.DAY_OF_YEAR, -1)
            }

            return c.time
        }
    }
}

fun main(args: Array<String>) {


    val c = Calendar.getInstance()
    c.set(Calendar.YEAR,2023)
    c.set(Calendar.MONTH,Calendar.MAY)
    c.set(Calendar.DAY_OF_MONTH,21)

    val fechaTest= c.time




    println(SemanaUtils.obtenerDia(fechaTest))
}
