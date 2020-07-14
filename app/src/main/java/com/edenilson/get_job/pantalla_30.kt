package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla30Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_30 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: UserActivity.getPerfil? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla30Binding>(
            inflater, R.layout.fragment_pantalla_30
            , container, false
        )

        (activity as UserActivity).supportActionBar?.title = ("Editar Perfil")

//        desde aqui le sampo el viewmodel
        modelPerfil = ViewModelProviders.of(activity!!).get(UserActivity.getPerfil::class.java)
//        los sampo a los InputText
        binding.inputNombre?.setText(modelPerfil!!._nombre.value.toString())
        binding.inputApellido?.setText(modelPerfil!!._apellido.value.toString())

//        Aqui valido que no me vengan los campos null
        if (modelPerfil!!._descripcion.value.toString() == "null" || modelPerfil!!._descripcion.value.toString() == "") {
            binding.tvDescripcionCompleta?.hint =
                "Breve descripcion de tu persona."

        } else {
            binding.inputDescripcion?.setText(modelPerfil!!._descripcion.value.toString())

        }
        if (modelPerfil!!._pais.value.toString() == "null"|| modelPerfil!!._pais.value.toString() == "") {

            binding.tvUbicacion?.hint =
                "Nombre del pais de nacimiento."

        } else {
            binding.inputPais?.setText(modelPerfil!!._pais.value.toString())

        }

//        Aqui iria la descripcion completa del usuario, hay que agregar al viewm model el campo
//        binding.tvDescripcionCompletaUsuario.text = modelPerfil!!._nombre.value.toString()
        val imagen = modelPerfil!!._foto.value.toString()
        binding.imgFotoPerfil?.let { Glide.with(this).load(imagen).into(it) }
        
        




        return binding.root
    }

}
