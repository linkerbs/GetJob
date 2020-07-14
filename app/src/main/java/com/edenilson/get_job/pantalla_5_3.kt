package com.edenilson.get_job

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla5Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.fragment_pantalla_5.*
import kotlinx.android.synthetic.main.ofertaslaborales_cards.*


/**
 * A simple [Fragment] subclass.
 */
private const val TAG: String = "pantalla_5_3"

class pantalla_5_3 : Fragment() {
    val db = FirebaseFirestore.getInstance()

    //    Este es para el viewmodel del pefil - no borrar
    private var modelPerfil: UserActivity.getPerfil? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla5Binding>(
            inflater, R.layout.fragment_pantalla_5
            , container, false
        )
//  Boton de ver mas, se elimina porque tode se sampa el scroolview
//        binding.tvVerMas.setOnClickListener { view: View ->
//            view.findNavController().navigate(R.id.action_pantalla_5_3_to_pantalla_6_3)
//        }


//        Muestro los datos con ViewModel del USUARIO
        val model = ViewModelProviders.of(activity!!).get(UserActivity.Communicator::class.java)

//        Asigno nombre de la activity
        (activity as UserActivity).supportActionBar?.title = (model._titulo.value.toString())
//        Asigno todos los valore a los textviews
        binding.tvNombreEmpresa.text = model._nombre_empresa.value.toString()
//        binding.tvFechaPublicacion.text = model._fecha.value.toString()
        binding.tvDescripcionEmpleo.text = model._descripcion.value.toString()
        binding.tvHabilidades?.text = model._habilidades.value.toString()
//        Aqui fuciono la experiencia con el spinner
        binding.tvExperiencia?.text = "${model._experiencia.value.toString() }  ${model._sp_experiencia.value.toString()}"
        binding.tvVacantes?.text = model._vacantes.value.toString()
        val imagen = model._foto.value.toString()


        Glide.with(this).load(imagen).into(binding.imageView3)

        binding.btnEnviarCv?.setOnClickListener {

            val db = FirebaseFirestore.getInstance()

            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                val correo = user.email
                val model =
                    ViewModelProviders.of(activity!!).get(UserActivity.Communicator::class.java)
                var titulo_oferta: String = model!!._titulo.value.toString()

                var habilidades_oferta: String = model!!._habilidades.value.toString()
                var correo_empresa: String = model!!._correo.value.toString()

                db.collection("aplicados")
                    .whereEqualTo("correo", correo_empresa)
                    .whereEqualTo("titulo", titulo_oferta)
                    .whereEqualTo("habilidades", habilidades_oferta)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {

                            db.collection("aplicados").document(document.id)
                                .update(
                                    "aplicados" , FieldValue.arrayUnion(correo)
                                )
                                .addOnSuccessListener { documentReference ->
                                    Log.d(
                                        ContentValues.TAG,
                                        "Se aplico a la oferta"
                                    )

                                }
                                .addOnFailureListener { e ->
                                    Log.w(
                                        ContentValues.TAG,
                                        "F",
                                        e
                                    )
                                } .addOnCompleteListener {
//                                Aqui iria si lo marco como favorito o no
                                    Toast.makeText(activity,"Ya has aplicado para esta oferta laboral",Toast.LENGTH_SHORT).show();
                                }

                        }
                        }



                    }

            }




        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(UserActivity.Communicator::class.java)


        var titulo_oferta: String = model!!._titulo.value.toString()

        var habilidades_oferta: String = model!!._habilidades.value.toString()
        var correo_empresa: String = model!!._correo.value.toString()

        modelPerfil = ViewModelProviders.of(activity!!).get(UserActivity.getPerfil::class.java)

        var correo_usuario: String = modelPerfil!!._correo.value.toString()

//Esto es para el boton del corazon


        star_button.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {

                Toast.makeText(context,"Se agrego a favoritos",Toast.LENGTH_SHORT).show()

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
                                    "usuarios" , FieldValue.arrayUnion(correo_usuario)
                                )
                                .addOnSuccessListener { documentReference ->
                                    Log.d(
                                        ContentValues.TAG,
                                        "Lo agregaste como favorito"
                                    )

                                }
                                .addOnFailureListener { e ->
                                    Log.w(
                                        ContentValues.TAG,
                                        "C mama en pantalla_5_3 al agregar a favoritos",
                                        e
                                    )
                                }
                                .addOnCompleteListener {
//                                Aqui iria si lo marco como favorito o no
                                    Log.d(TAG,"Ya le has dado de favorito")
                                }

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    }
//                -------------------------------------------------------------------------
            }
            override fun unLiked(likeButton: LikeButton) {
                Toast.makeText(context,"Se quito de favoritos",Toast.LENGTH_SHORT).show()

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
                                    "usuarios" , FieldValue.arrayRemove(correo_usuario)
                                )
                                .addOnSuccessListener { documentReference ->
                                    Log.d(
                                        ContentValues.TAG,
                                        "Lo agregaste como favorito"
                                    )

                                }
                                .addOnFailureListener { e ->
                                    Log.w(
                                        ContentValues.TAG,
                                        "C mama en pantalla_5_3 al agregar a favoritos",
                                        e
                                    )
                                }
                                .addOnCompleteListener {
//                                Aqui iria si lo marco como favorito o no
                                    Log.d(TAG,"Ya le has dado de favorito")
                                }

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    }
//                -------------------------------------------------------------------------
            }
        })
    }




}

