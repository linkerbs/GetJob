package com.edenilson.get_job

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla9Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * A simple [Fragment] subclass.
 */
class pantalla_9 : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla9Binding>(
            inflater, R.layout.fragment_pantalla_9
            , container, false)

        binding.btRegistroUsuario.setOnClickListener { view: View ->
            mAuth = FirebaseAuth.getInstance();

            var nombre: String = binding.etNombreUsuarios?.text.toString()
            var apellido: String = binding.etApellidoUsuarios?.text.toString()
            var correo: String = binding.etCorreoUsuarios?.text.toString()
            var contra: String = binding.etContraUsuarios?.text.toString()
            var contra2: String = binding.etContraAgainUsuarios?.text.toString()

            if(nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || contra2.isEmpty() || apellido.isEmpty())
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
                                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                                val user: FirebaseUser = mAuth.getCurrentUser()!!
                                Toast.makeText(
                                    activity, "Usuario creado con exito.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                view.findNavController().navigate(R.id.action_pantalla_9_to_pantalla_10)

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
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

}
