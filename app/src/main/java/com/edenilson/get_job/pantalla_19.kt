package com.edenilson.get_job

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla19Binding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */
class pantalla_19 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla19Binding>(
            inflater, R.layout.fragment_pantalla_19
            , container, false)

        (activity as UserActivity).supportActionBar?.title = ("Perfil")

        binding.btnModificarPerfilUsuario.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_pantalla_19_to_pantalla_30)
        }

        binding.btCerrar.setOnClickListener { view: View ->

            Firebase.auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            activity!!.startActivity(intent)
        }
        return binding.root


    }

}
