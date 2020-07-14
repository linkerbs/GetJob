package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edenilson.get_job.databinding.FragmentPantalla23Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_pantalla_23.*
import kotlinx.android.synthetic.main.fragment_pantalla_4.*

/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_23"

class pantalla_23 : Fragment(), (PostModel) -> Unit {
    //Ondas para el recyclerview
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    private var postList: List<PostModel> = ArrayList()
    private val postListAdapter: PostListAdapter = PostListAdapter(postList, this)

    //    Esto es para utilizarlo con el modelView
    private var model: UserActivity.Communicator? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla23Binding>(
            inflater, R.layout.fragment_pantalla_23
            , container, false
        )

//        binding.btnNotificacion.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_23_to_pantalla_223)
//        }

        (activity as UserActivity).supportActionBar?.title = ("Favoritos")
//        -----------------------------------------------------------------------------------
        loadPostData()

        // -----------------------------       reciclerview---------------------------------------------------------------

        model = ViewModelProviders.of(activity!!).get(UserActivity.Communicator::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //        inicializar el recylcer view
        ofertasFavoritas.layoutManager = LinearLayoutManager(context)
        ofertasFavoritas.adapter = postListAdapter
    }

    //Para cargar los datos de los favoritos
    private fun loadPostData() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val correo = user.email
            Log.d(TAG, "Correo del usuario: ${correo}")
            //    Obtengo la informacion de la oferta laboral
            if (correo != null) {
                firebaseRepo.getFavorito(correo).addOnCompleteListener {
                    if (it.isSuccessful) {
                        postList = it.result!!.toObjects(PostModel::class.java)
                        postListAdapter.postListItems = postList
                        postListAdapter.notifyDataSetChanged()
                        Log.d(TAG, "Se envio la data, Usuario para favorito: ${it.result}")
                    } else {
                        Log.d(TAG, "Error: ${it.exception!!.message}")

                    }
                }
            }

        }


    }

    override fun invoke(p1: PostModel) {
//        Esto es para utilizarlo con el modelView
        model!!.titulo(p1.titulo)
        model!!.foto(p1.foto)
        model!!.nombre_empresa(p1.nombre_empresa)
//        model!!.fecha(p1.fecha_publicacion)

        model!!.descripcion(p1.descripcion)
        model!!.habilidades(p1.habilidades)
        model!!.experiencia(p1.experiencia)
        model!!.vacantes(p1.vacantes)
        model!!.correo(p1.correo)
        model!!.pais(p1.pais)
//        Log.d(TAG,"A ver: ${p1.fecha_publicacion}")
////        model!!.setMsgCommunicator(p1.nombre_empresa,p1.descripcion)
//
////        val prueba =  p1.vacantes
//        Log.d(TAG,"a ver: ${model!!.foto(p1.foto)} si se sampo al invoke")

//        Lo direcciona al otro fragmento
        findNavController().navigate(R.id.pantalla_5_3)
    }
}



