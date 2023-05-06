package com.example.fitnessapp.utils

import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import java.text.SimpleDateFormat
import java.util.*

class SemanaUtils {


    companion object {


        val dateFormat = SimpleDateFormat("dd-MMMM-yyyy ")
        var dateFormayDay: SimpleDateFormat = SimpleDateFormat("EEEE")


        fun getFechaActual(): String {

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

                if (isRutinaDescansoOrFinalizada(semana.rutinas[contador])) {
                    contador++
                } else {
                    semanaIncompleta = true
                    posicion = contador
                }
            }
            return posicion
        }

        fun isRutinaDescansoOrFinalizada(rutina: Rutina): Boolean {
            return rutina.esDescanso || rutina.finalizada
        }

        /**  Monday is 1, Sunday is 7. */
        fun obtenerDia(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.DAY_OF_WEEK)
        }

        fun validarSemana(semana :Semana){

            val fechaActual= Date()
            val fechaSemana= Date(semana.fechaInicio.time)
            val c = Calendar.getInstance()
            c.time=fechaSemana


            var contador: Int = 0
            var laFechaEsMayor=false


            while (contador < semana.rutinas.size && !laFechaEsMayor){
              var fechaRutina=c.time

                if(fechaActual.after(fechaRutina)){
                    validarRutina(semana.rutinas[contador])

                }else{
                    laFechaEsMayor=true
                }
                c.add(Calendar.DATE,1)
            }




        }

        fun validarRutina(rutina:Rutina  ){

            if(!isRutinaDescansoOrFinalizada(rutina) && !rutina.faltoEjercicio){
                rutina.faltoEjercicio=true
            }

        }
    }
}

fun main(args: Array<String>){

    println(SemanaUtils.getFechaActual())
}
