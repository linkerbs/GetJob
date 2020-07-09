package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla12Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_12 : Fragment() {

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


        binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_12_to_pantalla_182)

        }

        (activity as CompanyActivity).supportActionBar?.title = ("Solicitudes")
        return binding.root
    }

}
