package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edenilson.get_job.databinding.FragmentPantalla11Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_pantalla_4.*


/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_11"

class pantalla_11 : Fragment(), (PostModel) -> Unit {
    //Ondas para el recyclerview
    private val firebaseRepo: FirebaseRepo = FirebaseRepo()
    private var postList: List<PostModel> = ArrayList()
    private val postListAdapter: PostListAdapter = PostListAdapter(postList, this)

    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla11Binding>(
            inflater, R.layout.fragment_pantalla_11
            , container, false
        )

        binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_fragment_pantalla_11_to_pantalla_18)

        }
//        C borro este boron,
//        binding.btnOfertaLaboralEmpresa.setOnClickListener {view: View ->
//            view.findNavController().navigate(R.id.action_fragment_pantalla_11_to_pantalla_13)
//        }

        //  setHasOptionsMenu(true)
        (activity as CompanyActivity).supportActionBar?.title = ("Mis ofertas")
// -----------------------------       reciclerview---------------------------------------------------------------


//        Cargo los datos
        loadPostData()


        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //        inicializar el recylcer view
        ofertasLaborales_list.layoutManager = LinearLayoutManager(context)
        ofertasLaborales_list.adapter = postListAdapter
    }

    /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         super.onCreateOptionsMenu(menu, inflater)
         inflater?.inflate(R.menu.menu_empresa, menu)
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return NavigationUI.onNavDestinationSelected(item!!,
             view!!.findNavController()) || super.onOptionsItemSelected(item)
     }*/


    //    Metodo para cargar todos los datos
    private fun loadPostData() {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val correo = user.email

            //    Obtengo la informacion de la oferta laboral
            if (correo != null) {
                firebaseRepo.getEmpresa(correo).addOnCompleteListener {
                    if (it.isSuccessful) {
                        postList = it.result!!.toObjects(PostModel::class.java)
                        postListAdapter.postListItems = postList
                        postListAdapter.notifyDataSetChanged()
                        Log.d(TAG, "Usuario: ${it.result}")
                    } else {
                        Log.d(TAG, "Error: ${it.exception!!.message}")

                    }
                }
            }


        }


//


    }

    override fun invoke(p1: PostModel) {
//        findNavController().navigate(R.id.action_fragment_pantalla_11_to_pantalla_13)

    }


}
