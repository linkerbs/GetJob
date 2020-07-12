package com.edenilson.get_job

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla16Binding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */
class pantalla_16 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: CompanyActivity.getPerfil? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla16Binding>(
            inflater, R.layout.fragment_pantalla_16
            , container, false)
        binding.btnModificarPerfilUsuario.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_17_to_pantalla_172)

        }

        binding.btCerrar.setOnClickListener { view: View ->
            Firebase.auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            activity!!.startActivity(intent)
        }

        (activity as CompanyActivity).supportActionBar?.title = ("Perfil")

//        Cargamos los datos del perfil desde el viewmodel
        modelPerfil = ViewModelProviders.of(activity!!).get(CompanyActivity.getPerfil::class.java)

        //        Esto es para utilizarlo con modelView
//        los sampo a los Input
        binding.tvNombreEmpresa?.text = modelPerfil!!._nombre.value.toString()

//      Ingreo la imagen
        val imagen = modelPerfil!!._foto.value.toString()
        binding.imgFotoEmpresa?.let { Glide.with(this).load(imagen).into(it) }

//Valido lo del null
        if (modelPerfil!!._descripcion.value.toString() == "" || modelPerfil!!._descripcion.value.toString() == "null") {
            binding.tvDescripcionCompletaEmpresa.setText("Edita tu perfil para ingresa una breve descripcion de la empresa .")
            Log.d("CompanyActivity","Se sampo al if")
        } else {
            binding.tvDescripcionCompletaEmpresa.text = modelPerfil!!._descripcion.value.toString()

        }
        if (modelPerfil!!._pais.value.toString() == "" || modelPerfil!!._pais.value.toString() == "null") {
            binding.tvUbicacionEmpresa.setText("Edita tu perfil para ingresar el nombre del pais de la empresa")
        } else {
            binding.tvUbicacionEmpresa.text = modelPerfil!!._pais.value.toString()


        }

        return binding.root
    }

}