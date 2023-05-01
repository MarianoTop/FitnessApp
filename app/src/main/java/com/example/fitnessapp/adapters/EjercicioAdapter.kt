package com.example.fitnessapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.entities.Ejercicio
import com.example.fitnessapp.fragments.PrevisualizacionEjercicioFragment

class EjercicioAdapter(private val context: Context, var ejercicios: MutableList<Ejercicio> ): RecyclerView.Adapter<EjercicioAdapter.EjercicioHolder>(){



    class EjercicioHolder(v: View) : RecyclerView.ViewHolder(v){


        private var view : View


        init {
            this.view=v

        }

        fun setImage( name:String,context :Context ){

            val imageExercise : ImageView = view.findViewById(R.id.exerciseImage)

            Glide
                .with(context)
                .load(name)
                .into(imageExercise);

        }

        fun setExerciseDescription (description: String){
            val txtExerciseDescription :TextView = view.findViewById(R.id.descriptionExerciseTxt)
            txtExerciseDescription.text=description
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.ejercicio_item,parent,false)
        return (EjercicioHolder(view))

    }



    override fun onBindViewHolder(holder: EjercicioHolder, position: Int) {
        holder.setImage(ejercicios[position].image,context)
        holder.setExerciseDescription(ejercicios[position].description+ " x"+ejercicios[position].cantidad)
    }

    override fun getItemCount(): Int {
        return ejercicios.size
    }

}