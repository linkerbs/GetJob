//Adaptador para el recyclerview de ofertas laborales pantalla 4 "sin logearse" y 24 "logeado como usuario"
package com.edenilson.get_job

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_pantalla_5.view.*
import kotlinx.android.synthetic.main.ofertaslaborales_cards.view.*
import kotlinx.coroutines.withContext

private const val TAG: String = "PostListAdapter"

class PostListAdapter(var postListItems: List<PostModel> , val clickListener:(PostModel)-> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(postModel: PostModel,clickListener:(PostModel)-> Unit ){

                itemView.oferta_titulo.text = postModel.titulo
                itemView.empresa_nombre.text = postModel.nombre_empresa
//                Log.d("asd", "Awuebo se sampo: ${postModel.nombre_empresa}")
//


            itemView.vacantes_cantidad.text = "Vacantes: "+postModel.vacantes
//            Log.d(TAG,"Nombre de la empresa: ${postModel.nombre_empresa}")

//            mostramos la foto


            Glide.with(itemView.context).load(postModel.foto).into(itemView.empresa_foto)

//            Esto seria cuando le de click le va enviar los datos a la pantalla5_3
            itemView.setOnClickListener{
                clickListener(postModel)
//
////                var Nombre_oferta:String = postModel.titulo
////                var Nombre_empresa:String = postModel.nombre_empresa
////                var Descripcion_empresa:String = postModel.descripcion
//                var Foto_empresa:String = postModel.foto
////
//                val intent = Intent(itemView.context, pantalla_5_3::class.java)
////                intent.putExtra("iNombre_oferta",Nombre_oferta)
////                intent.putExtra("iNombre_empresa",Nombre_empresa)
////                intent.putExtra("iDescripcion_empresa",Descripcion_empresa)
//                intent.putExtra("iFoto_empresa",Foto_empresa)
//
//                itemView.context.startActivity(intent)
//
//
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

//        holder.itemView.setOnClickListener{
//
//            val model = postListItems.get(position)
//
//            var gFoto_empresa:String = model.foto
//
//            val intent = Intent(holder.itemView.context, pantalla_24::class.java)
//
//            intent.putExtra("iFoto_empresa", gFoto_empresa)
//
//
//            holder.itemView.context.startActivity(intent)
//
//        }


    }
}