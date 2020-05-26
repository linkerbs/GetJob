package com.edenilson.get_job

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.edenilson.get_job.databinding.FragmentPantalla3Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_3 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val binding = DataBindingUtil.inflate<FragmentPantalla3Binding>(
            inflater, R.layout.fragment_pantalla_3
            , container, false)

      //  (activity as MainActivity).supportActionBar?.title = ("Eliga los filtros")
        return binding.root

    }


}
