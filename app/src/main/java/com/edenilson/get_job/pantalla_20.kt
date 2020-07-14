package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edenilson.get_job.databinding.FragmentPantalla20Binding
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_pantalla_20.*

/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_20"

class pantalla_20 : Fragment(), (PostModel) -> Unit {
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
        val binding = DataBindingUtil.inflate<FragmentPantalla20Binding>(
            inflater, R.layout.fragment_pantalla_20
            , container, false)



        (activity as UserActivity).supportActionBar?.title = ("Solicitudes enviadas")
        loadPostData()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //        inicializar el recylcer view
        listado_aplicados.layoutManager = LinearLayoutManager(context)
        listado_aplicados.adapter = postListAdapter
    }


    private fun loadPostData(){

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val correo = user.email
            Log.d(TAG,"El correo: ${correo}")

            //    Obtengo la informacion de la oferta laboral
            if (correo != null) {
                firebaseRepo.getMisAplicados(correo).addOnCompleteListener {
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
        Toast.makeText(activity,"Seccion en matenimiento ;(",Toast.LENGTH_LONG).show();
    }

}
