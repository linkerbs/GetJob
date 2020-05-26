package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla23Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_23 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla23Binding>(
            inflater, R.layout.fragment_pantalla_23
            , container, false
        )

     binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_23_to_pantalla_223)
        }

        (activity as UserActivity).supportActionBar?.title = ("Solicitudes enviadas")
        return binding.root
    }

}
