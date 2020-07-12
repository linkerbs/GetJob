package com.edenilson.get_job

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla19Binding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */
class pantalla_19 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: UserActivity.getPerfil? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla19Binding>(
            inflater, R.layout.fragment_pantalla_19
            , container, false
        )

        (activity as UserActivity).supportActionBar?.title = ("Perfil")

        binding.btnModificarPerfilUsuario.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_19_to_pantalla_30)
        }

        binding.btCerrar.setOnClickListener { view: View ->

            Firebase.auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            activity!!.startActivity(intent)
        }

        //        Esto es para utilizarlo con modelView
        modelPerfil = ViewModelProviders.of(activity!!).get(UserActivity.getPerfil::class.java)


//        los sampo a los Input
//        Aqui sumo el nombre con el appellido
//        Aqui valido que no me vengan los campos null y si vienen les seteo esa wea

        binding.tvNombreUsuario?.text =
            modelPerfil!!._nombre.value.toString() + modelPerfil!!._apellido.value.toString()

//Aqui valido lo de los campos nulos
        if (modelPerfil!!._descripcion.value.toString() == "null" || modelPerfil!!._descripcion.value.toString() == "") {
            binding.tvDescripcionCompletaUsuario.setText("Edita tu perfil para ingresa una breve descripcion de tu persona .")
        } else {
            binding.tvDescripcionCompletaUsuario.text = modelPerfil!!._descripcion.value.toString()

        }
        if (modelPerfil!!._pais.value.toString() == "null" || modelPerfil!!._pais.value.toString() == "") {
            binding.tvPais.setText("Edita tu perfil para ingresar el nombre del pais de nacimiento")
        } else {
            binding.tvPais.text = modelPerfil!!._pais.value.toString()


        }
//        Seteo la imagen
        val imagen = modelPerfil!!._foto.value.toString()
        binding.imgFotoPerfil?.let { Glide.with(this).load(imagen).into(it) }


        return binding.root


    }

}
