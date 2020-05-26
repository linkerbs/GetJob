package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla8Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_8 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla8Binding>(
            inflater, R.layout.fragment_pantalla_8
            , container, false)


        binding.btRegistroEmpresa.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_8_to_pantalla_10)
        }
        (activity as MainActivity).supportActionBar?.title = ("Registro empresa")
        return binding.root
    }

}
