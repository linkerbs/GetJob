package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla31Binding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_pantalla_13.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_31"

//Decclaro una variable del edite text del XML porque si no no furula
lateinit var Input_experiencia_update: EditText
lateinit var Layout_experiencia_update: TextInputLayout
var contador: Int = 0
val db = FirebaseFirestore.getInstance()

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
            , container, false
        )

        //Aqui le digo al binding que la variable antes es de esta pantalla
        Input_experiencia_update = binding.inputExperienciaUpdate!!
        Layout_experiencia_update = binding.layoutExperienciaUpdate!!




        (activity as CompanyActivity).supportActionBar?.title = ("Editar oferta laboral")

        //        desde aqui le sampo el viewmodel
        val model = ViewModelProviders.of(activity!!).get(CompanyActivity.Communicator::class.java)
//        Les seteo la data a los input Text
        binding.inputTituloOferta?.setText(model!!._titulo.value.toString())
        binding.inputDescripcion?.setText(model!!._descripcion.value.toString())
        binding.inputHabilidades?.setText(model!!._habilidades.value.toString())
        binding.inputExperienciaUpdate?.setText(model!!._experiencia.value.toString())
        binding.inputVacantes?.setText(model!!._vacantes.value.toString())

//        dato_spiner = model!!._sp_experiencia.value.toString()

//Esto seria para asignarle el valor al spinner pero no me sale
//        val stringArray =
//            resources.getStringArray(R.array.sp_experiencia)
//        val itemPosition = Arrays.asList(stringArray).indexOf(model!!._sp_experiencia.value.toString())
//        spiner_experiencia.setSelection(itemPosition)

//        ------------------------------------------------------------------------------------
        binding.btnGuardar?.setOnClickListener { view: View ->
//            Aqui tenes que jalar todos los input text y ACTUALIZARLO  a la oferta del mahe
            Log.d(TAG, "Contador: ${contador}")

//Valido la base, para que me sale la coleccion como tal
            var titulo_oferta: String = model!!._titulo.value.toString()
            var habilidades_oferta: String = model!!._habilidades.value.toString()
            var correo_empresa: String = model!!._correo.value.toString()

//Variables que va a actualizar
            var titulo: String = binding.inputTituloOferta?.text.toString()
            var desripcion: String = binding.inputDescripcion?.text.toString()
            var habilidades: String = binding.inputHabilidades?.text.toString()
            var vacantes: String = binding.inputVacantes?.text.toString()





            if (contador == 0) {
//                Lo gardarias con el nuevo valor del spinner -- actualizo experiencia y el spiner
                var experiencia: String = binding.inputExperienciaUpdate?.text.toString()
                var sp_experiencia: String = binding.spinerExperiencia?.selectedItem.toString()

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
                                .update(
                                    "descripcion", desripcion,
                                    "fecha_publicacion", java.util.Calendar.getInstance().time,
                                    "habilidades", habilidades,
                                    "vacantes", vacantes,
                                    "titulo", titulo,
                                    "experiencia", experiencia,
                                    "sp_experiencia", sp_experiencia
                                )
                                .addOnSuccessListener { documentReference ->
                                    Toast.makeText(
                                        activity,
                                        "Se actualizo la oferta laboral",
                                        Toast.LENGTH_LONG
                                    ).show();


                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        activity,
                                        "Ha sucedido un error, intentalo más tarde",
                                        Toast.LENGTH_LONG
                                    ).show();

                                }
                                .addOnCompleteListener {
                                    view.findNavController()
                                        .navigate(R.id.fragment_pantalla_11)
                                    contador = 0
                                }

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        Toast.makeText(
                            activity,
                            "Ha sucedido un error, intentalo más tarde",
                            Toast.LENGTH_LONG
                        ).show();
                    }


//-----------------------------------------------
            } else {
//         Lo guardarias como tal, con el valor del viewModel del spinner-- NO actualizo experiencia ni el spiner


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
                                .update(
                                    "descripcion", desripcion,
                                    "fecha_publicacion", java.util.Calendar.getInstance().time,
                                    "habilidades", habilidades,
                                    "vacantes", vacantes,
                                    "titulo", titulo
                                )
                                .addOnSuccessListener { documentReference ->
                                    Toast.makeText(
                                        activity,
                                        "Se actualizo la oferta laboral",
                                        Toast.LENGTH_LONG
                                    ).show();


                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        activity,
                                        "Ha sucedido un error, intentalo más tarde",
                                        Toast.LENGTH_LONG
                                    ).show();

                                }
                                .addOnCompleteListener {
                                    view.findNavController()
                                        .navigate(R.id.fragment_pantalla_11)
                                    contador = 0
                                }

                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(
                            activity,
                            "Ha sucedido un error, intentalo más tarde",
                            Toast.LENGTH_LONG
                        ).show();

                    }
//-----------------------------------------------------------------------------------------------
            }

        }
//----------------------------------------------------------------------------------------------
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //  Onda del spinner Spinner  para la experiencia
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


    }

    //    Esto ya es del spinner, la onda para que desabilite el inputtext
    class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            if (pos === 0) {
                Input_experiencia_update.isEnabled = false
                Layout_experiencia_update.isEnabled = false
                contador++
            } else {
                Input_experiencia_update.isEnabled = true
                Layout_experiencia_update.isEnabled = true
                contador--
            }

        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback

        }
    }

}
