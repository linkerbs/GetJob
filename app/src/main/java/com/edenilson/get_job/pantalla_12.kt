package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edenilson.get_job.databinding.FragmentPantalla12Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_pantalla_12.*

/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_12"
class pantalla_12 : Fragment(), (PostModel) -> Unit {
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private var postList: List <PostModel> = ArrayList()

    private val postListAdapter: PostListAdapter = PostListAdapter(postList,this)
    //    Esto es para utilizarlo con el modelView
    private var model: CompanyActivity.Communicator? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla12Binding>(
            inflater, R.layout.fragment_pantalla_12
            , container, false)
//C borro este boron,
//        binding.btnOfertaLaboralEmpresa.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_12_to_pantalla_32)
//        }


//        binding.btnNotificacion.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_12_to_pantalla_182)
//
//        }

        (activity as CompanyActivity).supportActionBar?.title = ("Solicitudes")
//        Esto es para utilizarlo con modelView
        model = ViewModelProviders.of(activity!!).get(CompanyActivity.Communicator::class.java)
        loadPostData()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //        inicializar el recylcer view
        solicitudesLaborales_list.layoutManager = LinearLayoutManager(context)
        solicitudesLaborales_list.adapter = postListAdapter
    }

    private fun loadPostData(){

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val correo = user.email

            //    Obtengo la informacion de la oferta laboral
            if (correo != null) {
                firebaseRepo.getAplicados(correo).addOnCompleteListener {
                    if (it.isSuccessful) {
                        postList = it.result!!.toObjects(PostModel::class.java)
                        postListAdapter.postListItems = postList
                        postListAdapter.notifyDataSetChanged()
                        Log.d(TAG, "Se envio la data. Usuario: ${it.result}")
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
//        model!!.pais(p1.pais)
        model!!.sp_experiencia(p1.sp_experiencia)
//        model!!.aplicados(p1.aplicados)
//        Log.d(TAG,"a VER: ${p1.aplicados}")
        Toast.makeText(activity,"Seccion en matenimiento ;(",Toast.LENGTH_LONG).show();


    }

}
