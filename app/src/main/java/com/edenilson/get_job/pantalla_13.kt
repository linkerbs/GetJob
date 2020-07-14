package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.os.Trace.isEnabled
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla13Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_pantalla_13.*

import java.util.*
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_13"

//Decclaro una variable del edite text del XML porque si no no furula
lateinit var Input_experiencia: EditText

class pantalla_13 : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    //    Variable donde guardo el string del spinner y guardarlo a la base
    lateinit var spinner: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla13Binding>(
            inflater, R.layout.fragment_pantalla_13
            , container, false
        )
//Aqui le digo al binding que la variable antes es de esta pantalla
        Input_experiencia = binding.tvExperiencias





        (activity as CompanyActivity).supportActionBar?.title = ("Publicar oferta")

        mAuth = FirebaseAuth.getInstance();
        val db = FirebaseFirestore.getInstance()



        binding.btnGuardar?.setOnClickListener { view: View ->

            var titulo: String = binding.tvTituloOferta?.text.toString()
            var descripcion: String = binding.tvDescripcionOferta?.text.toString()
            var habilidades: String = binding.tvHabilidades?.text.toString()
            var experiencias: String = binding.tvExperiencias?.text.toString()
            var vacantes: String = binding.etVacante?.text.toString()
//            Obtengo el valor del spinner selecionado
            var spinner: String =
                binding.spinerExperiencia.selectedItem.toString()




            if (titulo.isEmpty() || descripcion.isEmpty() || habilidades.isEmpty() || vacantes.isEmpty()) {

            } else {

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
                                var correo: String = document.getString("correo")!!


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
                                oferta["correo"] = correo
                                oferta["sp_experiencia"] = spinner

                                Log.d(TAG, "El spinner: ${spinner}")

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //  Spinner  para la experiencia
        // access the items of the list
        val array_tipo_empleo = resources.getStringArray(R.array.sp_experiencia)

        // access the spinner
        val sp_experiencia = spiner_experiencia
        sp_experiencia.onItemSelectedListener = SpinnerActivity()

        if (sp_experiencia != null) {

            val adapter = this.activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_dropdown_item, array_tipo_empleo
                )
            }

            sp_experiencia.adapter = adapter


        }


//        ----------------------------------------------
    }

    class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            if (pos === 0) {
                Input_experiencia.isEnabled = false
            } else {
                Input_experiencia.isEnabled = true
            }

        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }
    }


}



