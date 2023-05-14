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

    }

    //Se instancia el holder. Se copia y pega y se modifica con el contexto en que trabajo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemanaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.semana_item, parent, false)
        return (SemanaHolder(view));
    }

    //Acá se realiza el binding entre el modelo y la vista. Este método itera sobre la lista.
    override fun onBindViewHolder(holder: SemanaHolder, position: Int) {

        holder.setTituloSemana(position + 1);

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