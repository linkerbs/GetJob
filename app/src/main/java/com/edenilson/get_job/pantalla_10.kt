package com.edenilson.get_job

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.edenilson.get_job.databinding.FragmentPantalla10Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_10 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla10Binding>(
            inflater, R.layout.fragment_pantalla_10
            , container, false)

        binding.btIniciarSesionEntrar.setOnClickListener {view: View ->
            val intent = Intent(activity, CompanyActivity::class.java)
            activity!!.startActivity(intent)
        }

        binding.btIniciarSesionEntrar2.setOnClickListener {view: View ->
            val intent = Intent(activity, UserActivity::class.java)
            activity!!.startActivity(intent)
        }
        return binding.root
    }

}
