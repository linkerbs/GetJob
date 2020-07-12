package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edenilson.get_job.databinding.FragmentPantalla34Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_34 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla34Binding>(
            inflater, R.layout.fragment_pantalla_34,
            container, false
        )

        (activity as CompanyActivity).supportActionBar?.title = ("Mis Ofertas")
        return binding.root
    }

}
