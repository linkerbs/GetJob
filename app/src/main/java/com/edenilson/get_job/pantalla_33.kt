package com.edenilson.get_job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla33Binding

/**
 * A simple [Fragment] subclass.
 */
class pantalla_33 : Fragment() {
    //    Esto es para utilizarlo con el modelView
    private var model: CompanyActivity.Communicator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla33Binding>(
            inflater, R.layout.fragment_pantalla_33,
            container, false
        )

        

        (activity as CompanyActivity).supportActionBar?.title = ("Mis Ofertas")

        //        Muestro los datos con ViewModel DE EMPRESA
        val model = ViewModelProviders.of(activity!!).get(CompanyActivity.Communicator::class.java)

//        Asigno nombre de la activity
        (activity as CompanyActivity).supportActionBar?.title = (model._titulo.value.toString())
//        Asigno todos los valore a los textviews
        binding.tvNombreEmpresa.text = model._nombre_empresa.value.toString()
//        binding.tvFechaPublicacion.text = model._fecha.value.toString()
        binding.tvDescripcionEmpleo.text = model._descripcion.value.toString()
        binding.tvHabilidades?.text = model._habilidades.value.toString()
        binding.tvExperiencia?.text = model._experiencia.value.toString()
        binding.tvVacantes?.text = model._vacantes.value.toString()
        val imagen = model._foto.value.toString()


        Glide.with(this).load(imagen).into(binding.imageView3)

        binding.btnEditar.setOnClickListener {view :View ->
//                        view.findNavController().navigate(R.id.action_pantalla_152_to_pantalla_14)
            view.findNavController().navigate(R.id.action_pantalla_33_to_pantalla_31)

        }

        return binding.root

    }

}
