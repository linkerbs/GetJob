package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla16Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_16 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla16Binding>(
            inflater, R.layout.fragment_pantalla_16
            , container, false)
        binding.editText6.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_16_to_pantalla_17)

        }

        return binding.root
    }

}