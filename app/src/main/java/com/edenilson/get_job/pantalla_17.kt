package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla17Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_17 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: CompanyActivity.getPerfil? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla17Binding>(
            inflater, R.layout.fragment_pantalla_17
            , container, false
        )

        (activity as CompanyActivity).supportActionBar?.title = ("Editar Perfil")

        //        Cargamos los datos del perfil desde el viewmodel
        modelPerfil = ViewModelProviders.of(activity!!).get(CompanyActivity.getPerfil::class.java)
        binding.inputNombreEmpresa?.setText(modelPerfil!!._nombre.value.toString())

//      Ingreo la imagen
        val imagen = modelPerfil!!._foto.value.toString()
        binding.imgFotoEmpresa?.let {
            Glide.with(this).load(imagen).into(it)


            //        Aqui valido que no me vengan los campos null
            if (modelPerfil!!._descripcion.value.toString() == "null" || modelPerfil!!._descripcion.value.toString() == "") {
                binding.tvDescripcionCompleta?.hint =
                    "Breve descripcion de la empresa."

            } else {
                binding.inputDescripcion?.setText(modelPerfil!!._descripcion.value.toString())

            }
            if (modelPerfil!!._pais.value.toString() == "null" || modelPerfil!!._pais.value.toString() == "") {

                binding.tvUbicacion?.hint =
                    "Nombre del pais de la empresa."

            } else {
                binding.inputPais?.setText(modelPerfil!!._pais.value.toString())

            }
            return binding.root
        }
    }

}
