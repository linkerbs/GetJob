package com.edenilson.get_job

import android.R.attr.password
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.edenilson.get_job.databinding.FragmentPantalla10Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


/**
 * A simple [Fragment] subclass.
 */
class pantalla_10 : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla10Binding>(
            inflater, R.layout.fragment_pantalla_10
            , container, false
        )

        devolver()


        binding.btIniciarSesionEntrar.setOnClickListener { view: View ->
            mAuth = FirebaseAuth.getInstance();
            var correo: String = binding.etCorreos?.text.toString()
            var contra: String = binding.etContras?.text.toString()

            if (correo.isEmpty() || contra.isEmpty()) {
                Toast.makeText(
                    activity, "Complete los campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val addOnCompleteListener = mAuth.signInWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(Activity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user: FirebaseUser = mAuth.getCurrentUser()!!

                            db.collection("usuarios")
                                .whereEqualTo("correo", correo)
                                .get()
                                .addOnSuccessListener { documents ->
                                    for (document in documents) {
                                        if (document.getString("tipo") == "1") {
                                            val intent =
                                                Intent(activity, CompanyActivity::class.java)
                                            activity!!.startActivity(intent)
                                        } else {
                                            val intent =
                                                Intent(activity, UserActivity::class.java)
                                            activity!!.startActivity(intent)
                                        }
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.w(TAG, "Error getting documents: ", exception)
                                }


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                activity, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }

            }
        }


        (activity as MainActivity).supportActionBar?.title = ("Iniciar Sesion")
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        devolver()

    }


    fun devolver() {

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val name = user.email
            if (name != "") {


                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val name = user.email
                    db.collection("usuarios")
                        .whereEqualTo("correo", name)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                if (document.getString("tipo") == "1") {
                                    val intent = Intent(activity, CompanyActivity::class.java)
                                    activity!!.startActivity(intent)
                                } else {
                                    val intent = Intent(activity, UserActivity::class.java)
                                    activity!!.startActivity(intent)
                                }
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents: ", exception)
                        }

                }


            }

        }
    }


}
