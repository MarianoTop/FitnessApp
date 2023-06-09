package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.entities.EstadoRutina
import com.example.fitnessapp.entities.Rutina
import com.example.fitnessapp.entities.Semana
import com.google.android.material.snackbar.Snackbar

//El adaptador recibe una lista de elementos e implementa de la clase RecyclerView.Adapter
class SemanaAdapter(var semanaList : MutableList<Semana>, var onClick : (Int) -> Unit) : RecyclerView.Adapter<SemanaAdapter.SemanaHolder>(){

    //El holder se encarga de iteractuar con el xml del item. Recibe un item.
    class SemanaHolder(v : View) : RecyclerView.ViewHolder(v){
        //Hacemos una variable de tipo v intermedia.
        private var view : View
        init {
            this.view = v;
        }

        /*
        fun setAnimeName(name : String){
            val txtName : TextView = view.findViewById(R.id.textViewId);
            txtName.text = name;
        }

        fun getCard() : CardView {
            return view.findViewById(R.id.cardViewId)
        }
         */
        fun getSemanaCard() : CardView {
            return view.findViewById(R.id.semanaCardId)
        }

        fun getIniciarEjercicioButton () : Button {
            return view.findViewById(R.id.btnIniciarEjercicio)
        }

        fun getTituloSemanaTxtView () : TextView {
            return view.findViewById(R.id.txtViewTituloSemana);
        }

        fun setTituloSemana (numeroDeSemana : Int) {
            val txtView = getTituloSemanaTxtView();
            txtView.text = "Semana " + numeroDeSemana;
        }

        //GET de cada TextView correspondiente a los días de la Semana
        fun getTxtViewLunes () : TextView {
            return view.findViewById(R.id.txtViewLunes);
        }

        fun getTxtViewMartes () : TextView {
            return view.findViewById(R.id.txtViewMartes);
        }

        fun getTxtViewMiercoles () : TextView {
            return view.findViewById(R.id.txtViewMiercoles);
        }

        fun getTxtViewJueves () : TextView {
            return view.findViewById(R.id.txtViewJueves);
        }

        fun getTxtViewViernes () : TextView {
            return view.findViewById(R.id.txtViewViernes);
        }

        fun getTxtViewSabado () : TextView {
            return view.findViewById(R.id.txtViewSabado);
        }

        fun getTxtViewDomingo () : TextView {
            return view.findViewById(R.id.txtViewDomingo);
        }



        fun setColorFondo(rutina : Rutina, txtViewDia : TextView){
            if(rutina.estado == EstadoRutina.COMPLETADA.value){
                txtViewDia.setBackgroundResource(R.drawable.circulo_rojo);
            }else if(rutina.estado ==EstadoRutina.ES_DESCANSO.value){
                txtViewDia.setBackgroundResource(R.drawable.circulo_azul);
            }else if(rutina.estado ==EstadoRutina.AUSENTE.value){
                txtViewDia.setBackgroundResource(R.drawable.circulo_amarillo);
            }
        }

    }

    //Se instancia el holder. Se copia y pega y se modifica con el contexto en que trabajo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemanaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.semana_item, parent, false)
        return (SemanaHolder(view));
    }

    //Acá se realiza el binding entre el modelo y la vista. Este método itera sobre la lista.
    override fun onBindViewHolder(holder: SemanaHolder, position: Int) {
        println("LO QUE LLEGA:")
        println(semanaList)
        holder.setTituloSemana(position + 1);
        holder.setColorFondo(semanaList[position].rutinas[0], holder.getTxtViewLunes());
        holder.setColorFondo(semanaList[position].rutinas[1], holder.getTxtViewMartes());
        holder.setColorFondo(semanaList[position].rutinas[2], holder.getTxtViewMiercoles());
        holder.setColorFondo(semanaList[position].rutinas[3], holder.getTxtViewJueves());
        holder.setColorFondo(semanaList[position].rutinas[4], holder.getTxtViewViernes());
        holder.setColorFondo(semanaList[position].rutinas[5], holder.getTxtViewSabado());
        holder.setColorFondo(semanaList[position].rutinas[6], holder.getTxtViewDomingo());

        if(semanaList[position].estaFinalizada){
           // Snackbar.make(v, "Click en ${repository.ejercicios[position].description}", Snackbar.LENGTH_SHORT).show();
            holder.getIniciarEjercicioButton().setOnClickListener {
                onClick(-1)
            }
        }else{
            holder.getIniciarEjercicioButton().setOnClickListener {
                onClick(position)
            }
        }

    /*
    holder.setAnimeName(animesList[position].name)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
        */
    }
    //Devuelve el tamaño de la vista. Copio y pego.
    override fun getItemCount(): Int {
        return semanaList.size;
    }
}