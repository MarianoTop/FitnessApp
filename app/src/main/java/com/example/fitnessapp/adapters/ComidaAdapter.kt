package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Comida
import android.content.Context

class ComidaAdapter(private val context: Context, var comidas : MutableList<Comida>, var onClick : (Int) -> Unit) : RecyclerView.Adapter<ComidaAdapter.ComidaHolder>(){

    class ComidaHolder(v : View) : RecyclerView.ViewHolder(v){

        private var view : View
        init {
            this.view = v
        }

        fun setComidaName(name : String){

            val textName : TextView = view.findViewById(R.id.textNombre)
            textName.text = name
        }

        fun setComidaImage(imagen : String, context : Context ){

            val image : ImageView = view.findViewById(R.id.imageComida2)
            Glide
                .with(context)
                .load(imagen)
                .into(image);

}

        fun getCard() : CardView {
            return view.findViewById(R.id.cardComida)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comida_item,parent,false)
        return (ComidaHolder(view))
    }

    override fun onBindViewHolder(holder: ComidaHolder, position: Int) {
        holder.setComidaName(comidas[position].nombre)
        holder.setComidaImage(comidas[position].imagen, context)
        holder.getCard().setOnClickListener{
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return comidas.size
    }


}
