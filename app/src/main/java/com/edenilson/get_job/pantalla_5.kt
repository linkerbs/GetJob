package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla5Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_5 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla5Binding>(
            inflater, R.layout.fragment_pantalla_5
            , container, false
        )
//  Boton de ver mas, se elimina porque tode se sampa el scroolview
//        binding.tvVerMas.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_5_to_pantalla_6)
//        }

        (activity as MainActivity).supportActionBar?.title = ("Get Job")




        return binding.root


    }

}
