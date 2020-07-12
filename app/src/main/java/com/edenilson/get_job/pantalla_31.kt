package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.edenilson.get_job.databinding.FragmentPantalla31Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_31 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data dela oferta de la EMPRESA
    private var modelPerfil: CompanyActivity.Communicator? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla31Binding>(
            inflater, R.layout.fragment_pantalla_31
            , container, false)

        (activity as CompanyActivity).supportActionBar?.title = ("Editar oferta laboral")

        //        desde aqui le sampo el viewmodel
        val model = ViewModelProviders.of(activity!!).get(CompanyActivity.Communicator::class.java)
//        Les seteo la data a los input Text
        binding.inputTituloOferta?.setText(model!!._titulo.value.toString())
        binding.inputDescripcion?.setText(model!!._descripcion.value.toString())
        binding.inputHabilidades?.setText(model!!._habilidades.value.toString())
        binding.inputExperiencia?.setText(model!!._experiencia.value.toString())
        binding.inputVacantes?.setText(model!!._vacantes.value.toString())



        return binding.root
    }

}
