package com.edenilson.get_job

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla16Binding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
            view.findNavController().navigate(R.id.action_pantalla_17_to_pantalla_172)

        }

        binding.btCerrar.setOnClickListener { view: View ->
            Firebase.auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            activity!!.startActivity(intent)
        }
        (activity as CompanyActivity).supportActionBar?.title = ("Perfil")
        return binding.root
    }

}