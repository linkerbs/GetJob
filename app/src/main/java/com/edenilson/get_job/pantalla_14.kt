package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edenilson.get_job.databinding.FragmentPantalla14Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_14 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla14Binding>(
            inflater, R.layout.fragment_pantalla_14
            , container, false)

        (activity as CompanyActivity).supportActionBar?.title = ("Nombre del chat aqui")
        return binding.root
    }

}
