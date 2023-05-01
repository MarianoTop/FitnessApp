package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Rutina


//El adaptador recibe una lista de elementos e implementa de la clase RecyclerView.Adapter
class RutinaAdapter(var rutinaList : MutableList<Rutina>, var onClick : (Int) -> Unit) : RecyclerView.Adapter<RutinaAdapter.RutinaHolder>(){

    //El holder se encarga de iteractuar con el xml del item. Recibe un item.
    class RutinaHolder(v : View) : RecyclerView.ViewHolder(v){
        //Hacemos una variable de tipo v intermedia.
        private var view : View
        init {
            this.view = v;
        }

        fun setRutinaNumero(id : Long){
            val txtNumero : TextView = view.findViewById(R.id.textViewRutinaItem);
            txtNumero.text = id.toString();
        }

        fun setRutinaGrupo(name : String){
            val txtNumero : TextView = view.findViewById(R.id.textViewRutinaItem);
            txtNumero.text = name
        }

        fun getCard() : CardView {
            return view.findViewById(R.id.cardViewRutina)

        }

    }

    //Se instancia el holder. Se copia y pega y se modifica con el contexto en que trabajo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rutina_item, parent, false)
        return (RutinaHolder(view));
    }

    //Acá se realiza el binding entre el modelo y la vista. Este método itera sobre la lista.
    override fun onBindViewHolder(holder: RutinaHolder, position: Int) {
        //holder.setRutinaNumero(rutinaList[position].id)
        holder.setRutinaGrupo(rutinaList[position].grupoMuscular)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    //Devuelve el tamaño de la vista. Copio y pego.
    override fun getItemCount(): Int {
        return rutinaList.size;
    }
}
