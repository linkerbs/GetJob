package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla32Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_32 : Fragment() {

    //    Esto es para utilizarlo con el modelView
    private var model: CompanyActivity.Communicator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla32Binding>(
            inflater, R.layout.fragment_pantalla_32
            , container, false)

        //        Esto es para utilizarlo con modelView
        model = ViewModelProviders.of(activity!!).get(CompanyActivity.Communicator::class.java)

        //        Asigno nombre de la activity
        (activity as CompanyActivity).supportActionBar?.title = (model!!._titulo.value.toString())
        return binding.root


    }

}
