package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Semana


//El adaptador recibe una lista de elementos e implementa de la clase RecyclerView.Adapter
class SemanaAdapter(var semanaList : MutableList<Semana>, var onClick : (Int) -> Unit) : RecyclerView.Adapter<SemanaAdapter.SemanaHolder>(){

    //El holder se encarga de iteractuar con el xml del item. Recibe un item.
    class SemanaHolder(v : View) : RecyclerView.ViewHolder(v){
        //Hacemos una variable de tipo v intermedia.
        private var view : View
        init {
            this.view = v;
        }

        fun setSemanaNumero(id : Long){
            val txtNumero : TextView = view.findViewById(R.id.textView5);
            txtNumero.text = "Semana N°" + id.toString();
        }

        fun getCard() : CardView {
            return view.findViewById(R.id.cardViewSemana)
        }

    }

    //Se instancia el holder. Se copia y pega y se modifica con el contexto en que trabajo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemanaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.semana_item, parent, false)
        return (SemanaHolder(view));
    }

    //Acá se realiza el binding entre el modelo y la vista. Este método itera sobre la lista.
    override fun onBindViewHolder(holder: SemanaHolder, position: Int) {
        holder.setSemanaNumero(semanaList[position].id)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    //Devuelve el tamaño de la vista. Copio y pego.
    override fun getItemCount(): Int {
        return semanaList.size;
    }
}
