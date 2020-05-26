package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla9Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_9 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla9Binding>(
            inflater, R.layout.fragment_pantalla_9
            , container, false)

        binding.btRegistroUsuario.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_9_to_pantalla_10)
        }

        return binding.root
    }

}
