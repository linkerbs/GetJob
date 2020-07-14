package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edenilson.get_job.databinding.FragmentPantalla4Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_pantalla_4.*
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_24"
class pantalla_24 : Fragment(), (PostModel) -> Unit {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private var postList: List <PostModel> = ArrayList()


    private val postListAdapter: PostListAdapter = PostListAdapter(postList,this)

//    Esto es para utilizarlo con el modelView
    private var model: UserActivity.Communicator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla4Binding>(
            inflater, R.layout.fragment_pantalla_4
            , container, false)

//        Boton que te va a dirigir a la oferta laboral
//        binding.btnOferta.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_24_to_pantalla_5_3)
//        }

//        binding.btnFILTRO.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_24_to_pantalla_3_3)
//
//        }
//        binding.btnNotificacion.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_24_to_pantalla_224)
//
//        }

        (activity as UserActivity).supportActionBar?.title = ("Buscar Ofertas")
        // -----------------------------       reciclerview---------------------------------------------------------------


//        Cargo los datos
        loadPostData()

//        Esto es para utilizarlo con modelView
        model = ViewModelProviders.of(activity!!).get(UserActivity.Communicator::class.java)









//        val usuario = FirebaseAuth.getInstance().currentUser?.uid
//        Log.d(TAG,"uSUARIO: ${usuario}")
//        val user = FirebaseAuth.getInstance().currentUser
//        user?.let {
//            val id = user.uid
//
//            Log.d(TAG,"Id:${id}")
//
//        }
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection("usuarios")
//            .whereEqualTo("correo", "correo")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    val usuario = (document.getString("nombre"))
//                    Log.d(TAG,"Usuario: ${usuario}")
//
//
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents: ", exception)
//            }

        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"on ActivityCreated")

        //        inicializar el recylcer view
        ofertasLaborales_list.layoutManager = LinearLayoutManager(context)
        ofertasLaborales_list.adapter = postListAdapter
    }

    //    Metodo para cargar todos los datos
    private fun loadPostData(){


//    Obtengo la informacion de las oferta laborales
        firebaseRepo.getPostList().addOnCompleteListener{
            if(it.isSuccessful){
                postList = it.result!!.toObjects(PostModel::class.java)
                postListAdapter.postListItems = postList
                postListAdapter.notifyDataSetChanged()
                Log.d(TAG,"Usuario: ${it.result}")
            }else{
                Log.d(TAG,"Error: ${it.exception!!.message}")

            }
        }


//    Obtengo la informacion de la empresa de la oferta
//    firebaseRepo.getEmpresa().addOnCompleteListener{
//        if(it.isSuccessful){
//            postList = it.result!!.toObjects(PostModel::class.java)
//            postListAdapter.postListItems = postList
//            postListAdapter.notifyDataSetChanged()
//                    Log.d(TAG,"Empresa: ${it.result}")
//        }else{
//            Log.d(TAG,"Error: ${it.exception!!.message}")
//
//        }
//    }


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
        model!!.sp_experiencia(p1.sp_experiencia)
//        Log.d(TAG,"A ver: ${p1.fecha_publicacion}")
////        model!!.setMsgCommunicator(p1.nombre_empresa,p1.descripcion)
//
////        val prueba =  p1.vacantes
//        Log.d(TAG,"a ver: ${model!!.foto(p1.foto)} si se sampo al invoke")

//        Lo direcciona al otro fragmento
        findNavController().navigate(R.id.pantalla_5_3)
//        Le cambia el titulo al otro fragmento
//        (activity as UserActivity).supportActionBar?.title = (p1.nombre_empresa)

    }

}
