package com.edenilson.get_job

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla33Binding
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_33"

class pantalla_33 : Fragment() {
    //    Esto es para utilizarlo con el modelView
    private var model: CompanyActivity.Communicator? = null
    val db = FirebaseFirestore.getInstance()

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
        binding.tvExperiencia?.text = "${model._experiencia.value.toString() }  ${model._sp_experiencia.value.toString()}"
        binding.tvVacantes?.text = model._vacantes.value.toString()
        val imagen = model._foto.value.toString()


        Glide.with(this).load(imagen).into(binding.imageView3)

        binding.btnEditar.setOnClickListener {view :View ->
//                        view.findNavController().navigate(R.id.action_pantalla_152_to_pantalla_14)
            view.findNavController().navigate(R.id.action_pantalla_33_to_pantalla_31)

        }

//        Borrar oferta laboral
        val modelo = ViewModelProviders.of(activity!!).get(CompanyActivity.Communicator::class.java)

        //Valido la base, para que me sale la coleccion como tal
        var titulo_oferta: String = model!!._titulo.value.toString()
        var habilidades_oferta: String = model!!._habilidades.value.toString()
        var correo_empresa: String = model!!._correo.value.toString()

        binding.btnEliminar?.setOnClickListener { view: View ->

            db.collection("ofertas")
                .whereEqualTo("correo", correo_empresa)
                .whereEqualTo("titulo", titulo_oferta)
                .whereEqualTo("habilidades", habilidades_oferta)
                .get()
                .addOnSuccessListener { documents ->

                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }

                    for (document in documents) {


                        db.collection("ofertas").document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(
                                    activity,
                                    "Se ha borrado la oferta laboral",
                                    Toast.LENGTH_LONG
                                ).show();

                                view.findNavController()
                                    .navigate(R.id.fragment_pantalla_11)
                            }

                    }
                }

        }

        return binding.root

    }



}
