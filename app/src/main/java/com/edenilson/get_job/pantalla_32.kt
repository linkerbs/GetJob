package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edenilson.get_job.databinding.FragmentPantalla32Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_32 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla32Binding>(
            inflater, R.layout.fragment_pantalla_32
            , container, false)

        (activity as CompanyActivity).supportActionBar?.title = ("Postulante")
        return binding.root
    }

}
