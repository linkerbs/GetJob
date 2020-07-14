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
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla30Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_pantalla_19.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class pantalla_30 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: UserActivity.getPerfil? = null
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
        if (modelPerfil!!._pais.value.toString() == "null" || modelPerfil!!._pais.value.toString() == "") {

            binding.tvUbicacion?.hint =
                "Nombre del pais de nacimiento."

        } else {
            binding.inputPais?.setText(modelPerfil!!._pais.value.toString())

        }

//        Aqui iria la descripcion completa del usuario, hay que agregar al viewm model el campo
//        binding.tvDescripcionCompletaUsuario.text = modelPerfil!!._nombre.value.toString()
        val imagen = modelPerfil!!._foto.value.toString()
        binding.imgFotoPerfil?.let {
            //Glide.with(this).load(imagen).into(it)
            fotito = imagen
        }


        binding.btnCambiarFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }


        binding.button4.setOnClickListener {


            val nombre = binding.inputNombre.text.toString()
            val apellido = binding.inputApellido.text.toString()
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
                                                    "apellido", apellido,
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
                                            "apellido", apellido,
                                            "pais", pais,
                                            "descripcion", descripcion,
                                            "foto", fotito
                                        )
                                }

                        }
            }


            view?.findNavController()?.navigate(R.id.action_pantalla_30_to_pantalla_19)
        }




        return binding.root
    }

}
