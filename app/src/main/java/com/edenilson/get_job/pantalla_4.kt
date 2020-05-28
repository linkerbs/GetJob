package com.edenilson.get_job

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla4Binding
import kotlinx.android.synthetic.main.fragment_pantalla_4.*

/**
 * A simple [Fragment] subclass.
 */
class pantalla_4 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla4Binding>(
            inflater, R.layout.fragment_pantalla_4
            , container, false)

        binding.btnOferta.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_4_to_pantalla_5)
        }
        binding.btnFILTRO.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_4_to_pantalla_3)

        }
        /*binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_4_to_pantalla_222)

        }*/
        (activity as MainActivity).supportActionBar?.title = ("Get Job")
        return binding.root


    }

}
