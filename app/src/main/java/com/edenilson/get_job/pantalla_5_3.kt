package com.edenilson.get_job

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla5Binding
import kotlinx.android.synthetic.main.fragment_pantalla_5.*
import kotlinx.android.synthetic.main.ofertaslaborales_cards.*


/**
 * A simple [Fragment] subclass.
 */
class pantalla_5_3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla5Binding>(
            inflater, R.layout.fragment_pantalla_5
            , container, false
        )

        binding.tvVerMas.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_5_3_to_pantalla_6_3)
        }

        (activity as UserActivity).supportActionBar?.title = ("Get Job")


//        Muestro los datos con ViewModel
        val model= ViewModelProviders.of(activity!!).get(UserActivity.Communicator::class.java)
        binding.tvNombreEmpresa.text = model._nombre_empresa.value.toString()
        binding.tvDescripcionEmpleo.text = model._descripcion.value.toString()
        val imagen = model._foto.value.toString()


            Glide.with(this).load(imagen).into(binding.imageView3)
//     Log.d("pantalla_5_3","${  Glide.with(this).load(model.message).into(imageView3)}")


//        Obtengo los datos por medio de un intent
//        var intent= Intent(activity , pantalla_5_3::class.java)
////        val Nombre_oferta = intent.getStringExtra("iNombre_oferta")
////        val Nombre_empresa = intent.getStringExtra("iNombre_empresa")
////        val Descripcion_empresa = intent.getStringExtra("iDescripcion_empresa")
//        val Foto_empresa = intent.getStringExtra( "iFoto_empresa")



//        tv_nombre_empresa.text = Nombre_oferta
//        tv_nombre_empresa.text = Nombre_empresa
//        tv_descripcion_empleo.text = Descripcion_empresa

//        Glide.with(this).load(Foto_empresa).into(imageView3)



        return binding.root


    }







}
