package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edenilson.get_job.databinding.FragmentPantalla4Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_pantalla_4.*


/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "Pantalla4"
class pantalla_4 : Fragment(), (PostModel) -> Unit {

    private val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private var postList: List <PostModel> = ArrayList()

    private val postListAdapter: PostListAdapter = PostListAdapter(postList,this)

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
//            view.findNavController().navigate(R.id.action_pantalla_4_to_pantalla_5)
//        }
//
        binding.btnFILTRO.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_4_to_pantalla_3)

        }
        /*binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_4_to_pantalla_222)

        }*/
        (activity as MainActivity).supportActionBar?.title = ("Get Job")
// -----------------------------       reciclerview---------------------------------------------------------------


//        Cargo los datos
        loadPostData()
//        los ingreso al recyclerview



        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //        inicializar el recylcer view
        ofertasLaborales_list.layoutManager = LinearLayoutManager(context)
        ofertasLaborales_list.adapter = postListAdapter
    }

//    Metodo para cargar todos los datos
    private fun loadPostData(){
        val uid = FirebaseAuth.getInstance().uid ?: ""

//    Obtengo la informacion de la oferta laboral
        firebaseRepo.getPostList().addOnCompleteListener{
            if(it.isSuccessful){
                postList = it.result!!.toObjects(PostModel::class.java)
                postListAdapter.postListItems = postList
                postListAdapter.notifyDataSetChanged()
//                    Log.d(TAG,"Usuario: ${it.result}")
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
//                    Log.d("Empresa","Empresa: ${it.result}")
//        }else{
//            Log.d(TAG,"Error: ${it.exception!!.message}")
//
//        }
//    }


    }

    override fun invoke(p1: PostModel) {
//        Toast.makeText(context,"Clickked on item: ${p1.titulo}", Toast.LENGTH_LONG).show()
//        Log.d(TAG,"${p1.titulo}")
//        (activity as MainActivity).supportActionBar?.title = (p1.titulo)

        FirebaseAuth.getInstance().addAuthStateListener { user ->

            if(user.uid != ""){
                Log.d(TAG,"No se sampo: ${user}")
                view?.findNavController()?.navigate(R.id.pantalla_7)
            }else{
                Log.d(TAG,"SI se sampo: ${user}")
                findNavController().navigate(R.id.action_pantalla_4_to_pantalla_5)


            }
        }

    }

}
