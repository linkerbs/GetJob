package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla9Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_pantalla_9.*
import java.util.*
import kotlin.collections.HashMap

/**
 * A simple [Fragment] subclass.
 */
class pantalla_9 : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var selectedPhoto: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhoto = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedPhoto)

            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla9Binding>(
            inflater, R.layout.fragment_pantalla_9
            , container, false
        )


        binding.btElegir?.setOnClickListener { view: View ->

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }


        binding.btRegistroUsuario.setOnClickListener { view: View ->
            mAuth = FirebaseAuth.getInstance();
            val db = FirebaseFirestore.getInstance()

            var nombre: String = binding.etNombreUsuarios?.text.toString()
            var apellido: String = binding.etApellidoUsuarios?.text.toString()
            var correo: String = binding.etCorreoUsuarios?.text.toString()
            var contra: String = binding.etContraUsuarios?.text.toString()
            var contra2: String = binding.etContraAgainUsuarios?.text.toString()

            if (nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || contra2.isEmpty() || apellido.isEmpty()) {
                Toast.makeText(activity, "Rellene todos los campos", Toast.LENGTH_LONG).show()
            } else if (contra != contra2) {
                Toast.makeText(activity, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            } else {
                mAuth.createUserWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(
                        Activity(),
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                                val user: FirebaseUser = mAuth.getCurrentUser()!!
                                Toast.makeText(
                                    activity, "Usuario creado con exito.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                uploadImage(nombre, correo, apellido)

                                view.findNavController()
                                    .navigate(R.id.action_pantalla_9_to_pantalla_10)

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(
                                    ContentValues.TAG,
                                    "createUserWithEmail:failure",
                                    task.exception
                                )
                                Toast.makeText(
                                    activity, "Fallo en la creacion.",
                                    Toast.LENGTH_SHORT
                                ).show()


                            }


                        })
            }


        }
        (activity as MainActivity).supportActionBar?.title = ("Registro")
        return binding.root
    }


    private fun uploadImage(nombre: String, correo: String, apellido: String) {
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/users/$filename")
        var devolver: String = ""
        val db = FirebaseFirestore.getInstance()

        ref.putFile(selectedPhoto!!).addOnSuccessListener { it ->
            Log.d("Registro", "Se subio la imagen: ${it.metadata?.path}")

            ref.downloadUrl.addOnSuccessListener {
                Log.d("Registro", "Se subio la imagen: $it")
                devolver = it.toString()

                val usuario: MutableMap<String, Any> =
                    HashMap()
                usuario["nombre"] = nombre
                usuario["apellido"] = apellido
                usuario["correo"] = correo
                usuario["tipo"] = "1"
                usuario["foto"] = devolver

                db.collection("usuarios")
                    .add(usuario)
                    .addOnSuccessListener { documentReference ->
                        Log.d(
                            ContentValues.TAG,
                            "Se agrego el documento con ID " + documentReference.id
                        )
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
    }
}


