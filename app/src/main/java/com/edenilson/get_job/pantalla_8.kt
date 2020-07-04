package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla8Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


/**
 * A simple [Fragment] subclass.
 */
class pantalla_8 : Fragment() {

    private lateinit var mAuth: FirebaseAuth





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla8Binding>(
            inflater, R.layout.fragment_pantalla_8
            , container, false)

        mAuth = FirebaseAuth.getInstance();
        val db = FirebaseFirestore.getInstance()
        binding.btRegistroEmpresa.setOnClickListener { view: View ->



            var nombre: String = binding.etNombreEmpresas?.text.toString()
            var correo: String = binding.etCorreoEmpresas?.text.toString()
            var contra: String = binding.etContraEmpresas?.text.toString()
            var contra2: String = binding.etContraAgainEmpresas?.text.toString()

            if(nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || contra2.isEmpty())
            {
                Toast.makeText(activity, "Rellene todos los campos", Toast.LENGTH_LONG).show()
            }
            else if (contra != contra2){
                Toast.makeText(activity, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
            else {
                mAuth.createUserWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(
                        Activity(),
                        OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                val user: FirebaseUser = mAuth.getCurrentUser()!!
                                Toast.makeText(
                                    activity, "Usuario creado con exito.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val usuario: MutableMap<String, Any> =
                                    HashMap()
                                usuario["nombre"] = nombre
                                usuario["correo"] = correo
                                usuario["tipo"] = "1"

                                db.collection("usuarios")
                                    .add(usuario)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(
                                            TAG,
                                            "Se agrego el documento con ID " + documentReference.id
                                        )
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            TAG,
                                            "C mamo algo",
                                            e
                                        )
                                    }


                                view.findNavController().navigate(R.id.action_pantalla_8_to_pantalla_10)

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    activity, "Fallo en la creacion.",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }


                        })
            }



        }


        (activity as MainActivity).supportActionBar?.title = ("Registro empresa")



        return binding.root
    }

}

private operator fun FirebaseUser.set(s: String, value: String) {

}
