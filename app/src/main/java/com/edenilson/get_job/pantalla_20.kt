package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla20Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_20 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla20Binding>(
            inflater, R.layout.fragment_pantalla_20
            , container, false)

        binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_7_to_pantalla_22)
        }
        binding.chatEmpresa1.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_7_to_pantalla_21)
        }
        binding.chatEmpresa2.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_7_to_pantalla_21)
        }

        return binding.root
    }

}
