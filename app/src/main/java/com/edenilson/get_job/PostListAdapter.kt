//Adaptador para el recyclerview de ofertas laborales pantalla 4
package com.edenilson.get_job

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.ofertaslaborales_cards.view.*

class PostListAdapter(var postListItems: List<PostModel>, val clickListener:(PostModel)-> Unit  ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(postModel: PostModel,clickListener:(PostModel)-> Unit){

            itemView.oferta_titulo.text = postModel.titulo
            itemView.empresa_nombre.text = postModel.nombre_empresa
            itemView.vacantes_cantidad.text = "Vacantes: "+postModel.vacantes
//            mostramos la foto
            Glide.with(itemView.context).load(postModel.foto).into(itemView.empresa_foto)

//            Esto seria cuando le de click
            itemView.setOnClickListener{
                clickListener(postModel)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        Mostramos todas las ofertas que tengan "se diseñan"
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ofertaslaborales_cards,parent,false)

        return  ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postListItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder).bind(postListItems[position],clickListener)
    }
}