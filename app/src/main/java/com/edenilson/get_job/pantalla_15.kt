package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla15Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_15 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla15Binding>(
            inflater, R.layout.fragment_pantalla_15
            , container, false)


        binding.btnNotificacion.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_152_to_pantalla_18)

        }
        (activity as CompanyActivity).supportActionBar?.title = ("Chat")
        return binding.root
    }

}
