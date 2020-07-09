package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla13Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_pantalla_31.*
import kotlin.math.exp

/**
 * A simple [Fragment] subclass.
 */
class pantalla_13 : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla13Binding>(
            inflater, R.layout.fragment_pantalla_13
            , container, false
        )







        (activity as CompanyActivity).supportActionBar?.title = ("Publicar oferta")

        mAuth = FirebaseAuth.getInstance();
        val db = FirebaseFirestore.getInstance()



        binding.btnGuardar?.setOnClickListener { view: View ->

            var titulo: String = binding.tvTituloOferta?.text.toString()
            var descripcion: String = binding.tvDescripcionOferta?.text.toString()
            var habilidades: String = binding.tvHabilidades?.text.toString()
            var experiencias: String = binding.tvExperiencias?.text.toString()
            var vacantes: String = binding.etVacante?.text.toString()



            if (titulo.isEmpty() || descripcion.isEmpty() || habilidades.isEmpty() || experiencias.isEmpty() || vacantes.isEmpty()) {

        }else
            {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val name = user.email

                    db.collection("usuarios")
                        .whereEqualTo("correo", name)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                var nombre: String = document.getString("nombre")!!
                                var foto: String = document.getString("foto")!!

                                val oferta: MutableMap<String, Any> =
                                    HashMap()
                                oferta["titulo"] = titulo
                                oferta["descripcion"] = descripcion
                                oferta["experiencia"] = experiencias
                                oferta["habilidades"] = habilidades
                                oferta["vacantes"] = vacantes
                                oferta["fecha_publicacion"] = java.util.Calendar.getInstance().time
                                oferta["nombre_empresa"] = nombre
                                oferta["foto"] = foto


                                db.collection("ofertas")
                                    .add(oferta)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(
                                            ContentValues.TAG,
                                            "Se agrego el documento con ID " + documentReference.id
                                        )
                                        view?.findNavController()
                                            .navigate(R.id.action_pantalla_13_to_fragment_pantalla_11)
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            ContentValues.TAG,
                                            "C mamo algo",
                                            e
                                        )
                                    }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }


                }


            }
            }

        return binding.root
    }


}

