package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
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
import com.edenilson.get_job.databinding.FragmentPantalla17Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class pantalla_17 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: CompanyActivity.getPerfil? = null
    private lateinit var mAuth: FirebaseAuth
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var fotito: String? = null
    var selectedPhoto: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhoto = data.data

        }
    }

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
        fotito = imagen
        binding.imgFotoEmpresa?.let {
           // Glide.with(this).load(imagen).into(it)


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

            binding.btnCambiarFoto.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 0)
            }



            binding.btnActualizarPerfil.setOnClickListener {
                val nombre = binding.inputNombreEmpresa.text.toString()
                val pais = binding.inputPais.text.toString()
                val descripcion = binding.inputDescripcion.text.toString()
                val db = FirebaseFirestore.getInstance()

                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val correo = user.email
                    val filename = UUID.randomUUID().toString()
                    val ref = FirebaseStorage.getInstance().getReference("/users/$filename")


                    if (selectedPhoto != null) {
                        ref.putFile(selectedPhoto!!).addOnSuccessListener { it ->
                            Log.d("Registro", "Se subio la imagen: ${it.metadata?.path}")
                            ref.downloadUrl.addOnSuccessListener {
                                db.collection("usuarios")
                                    .whereEqualTo("correo", correo)
                                    .get()
                                    .addOnSuccessListener { documents ->

                                        for (document in documents) {
                                            Log.d("Registro", "Se subio la imagen: ${it}")
                                            db.collection("usuarios").document(document.id)
                                                .update(
                                                    "nombre", nombre,
                                                    "pais", pais,
                                                    "descripcion", descripcion,
                                                    "foto", it.toString()
                                                )
                                        }


                                    }
                            }
                        }.addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Exploto el pdf",
                                e
                            )
                        }
                    } else
                        db.collection("usuarios")
                            .whereEqualTo("correo", correo)
                            .get()
                            .addOnSuccessListener { documents ->

                                for (document in documents) {
                                    Log.d("Registro", "Se subio la imagen: ${it}")
                                    db.collection("usuarios").document(document.id)
                                        .update(
                                            "nombre", nombre,

                                            "pais", pais,
                                            "descripcion", descripcion,
                                            "foto", fotito
                                        )
                                }

                            }
                }


                view?.findNavController()?.navigate(R.id.action_pantalla_172_to_pantalla_17)
            }
        }
        return binding.root
    }


}


