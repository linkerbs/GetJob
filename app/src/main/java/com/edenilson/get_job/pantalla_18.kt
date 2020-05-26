package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edenilson.get_job.databinding.FragmentPantalla18Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_18 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla18Binding>(
            inflater, R.layout.fragment_pantalla_18
            , container, false)

        //(activity as CompanyActivity).supportActionBar?.title = ("Notificaciones")
        return binding.root
    }

}
