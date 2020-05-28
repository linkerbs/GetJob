package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.edenilson.get_job.databinding.FragmentPantalla30Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_30 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla30Binding>(
            inflater, R.layout.fragment_pantalla_30
            , container, false)

        (activity as UserActivity).supportActionBar?.title = ("Editar Usuario")
        return binding.root
    }

}
