package com.edenilson.get_job

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla13Binding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_pantalla_31.*

/**
 * A simple [Fragment] subclass.
 */
class pantalla_13 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla13Binding>(
            inflater, R.layout.fragment_pantalla_13
            , container, false)







        (activity as CompanyActivity).supportActionBar?.title = ("Publicar oferta")

        binding.btnGuardar?.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_13_to_fragment_pantalla_11)
        }


        return binding.root
    }


}

