package com.edenilson.get_job

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.edenilson.get_job.databinding.FragmentPantalla11Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


/**
 * A simple [Fragment] subclass.
 */
class pantalla_11 : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla11Binding>(
            inflater, R.layout.fragment_pantalla_11
            , container, false)

        binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_fragment_pantalla_11_to_pantalla_18)

        }

        binding.btnOfertaLaboralEmpresa.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_fragment_pantalla_11_to_pantalla_13)
        }

      //  setHasOptionsMenu(true)
        (activity as CompanyActivity).supportActionBar?.title = ("Mis ofertas")
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val name = user.email
            Toast.makeText(
                activity, name,
                Toast.LENGTH_LONG
            ).show()
        }


        return binding.root


    }

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_empresa, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController()) || super.onOptionsItemSelected(item)
    }*/


}
