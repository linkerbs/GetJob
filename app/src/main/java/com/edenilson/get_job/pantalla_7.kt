package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla7Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_7 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla7Binding>(
            inflater, R.layout.fragment_pantalla_7
            , container, false)

        binding.btLoginEmpresa.setOnClickListener{view:View ->
            view.findNavController().navigate(R.id.action_pantalla_7_to_pantalla_8)
        }
        binding.btLoginUsuario.setOnClickListener { view: View->
            view.findNavController().navigate(R.id.action_pantalla_7_to_pantalla_9)
        }

        binding.btIniciarSesion.setOnClickListener { view: View->
            view.findNavController().navigate(R.id.action_pantalla_7_to_pantalla_10)
        }

        (activity as MainActivity).supportActionBar?.title = ("¿Tienes una cuenta?")
        return binding.root
    }

}
