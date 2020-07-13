package com.edenilson.get_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "UserActivity"

class UserActivity : AppCompatActivity() {
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: UserActivity.getPerfil? = null
    private var modelFavorito: UserActivity.Communicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val NavController = this.findNavController(R.id.MyNavHostFragment3)
        NavigationUI.setupActionBarWithNavController(this, NavController)



        findViewById<BottomNavigationView>(R.id.bottomNav)
            .setupWithNavController(NavController)

//        llevo la informacion a la pantalla_19 y pantalla_30

        modelPerfil = ViewModelProviders.of(this).get(UserActivity.getPerfil::class.java)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val correo = user.email
//            Log.d(TAG, "Usuario: ${user.uid}")

//    Obtengo la informacion del perfil del USUARIO
            if (correo != null) {

                firebaseFirestore.collection("usuarios")
                    .whereEqualTo("correo", correo)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
//                            Log.d(TAG, "${document.id} => ${document.data}")
//                            binding.tvNombreUsuario.text = document.getString("nombre")
//                            Glide.with(this).load(document.getString("foto")).into(binding.imgBtnPerfilUsuario)
                            ("${document.getString("nombre")} ")?.let { it1 ->
                                modelPerfil?.nombre(
                                    it1
                                )
                            }
                            ("${document.getString("apellido")}")?.let { it1 ->
                                modelPerfil?.apellido(
                                    it1
                                )
                            }
                            ("${document.getString("pais")}")?.let { it1 ->
                                modelPerfil?.pais(
                                    it1
                                )
                            }
                            ("${document.getString("descripcion")}")?.let { it1 ->
                                modelPerfil?.descripcion(
                                    it1
                                )
                            }
                            ("${document.getString("correo")}")?.let { it1 ->
                                modelPerfil?.correo(
                                    it1
                                )
                            }
                            document.getString("foto")?.let { it1 -> modelPerfil?.foto(it1) }
//                            modelPerfil?.nombre(document.getString("nombre"))
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents for UserActivity: ", exception)
                    }
            }


        }
//---------------------------------------------------------------------------------------------
//Seccion para traer la data de favoritos
        //        llevo la informacion a la pantalla_23

//        modelFavorito = ViewModelProviders.of(this).get(UserActivity.Communicator::class.java)
//
////        ocupo el mismo user de arribita
//        user?.let {
//            val correo = user.email
//            Log.d(TAG, "Usuario para favoritos: ${user.uid}")
//
////    Obtengo la informacion del perfil del USUARIO
//            if (correo != null) {
//
//                firebaseFirestore.collection("ofertas")
//                    .whereArrayContains("usuarios", correo)
//                    .get()
//                    .addOnSuccessListener { documents ->
//                        for (document in documents) {
//                            Log.d(TAG,"Desde aqui recibo la data de favoritos")
//                            Log.d(TAG, "${document.id} => ${document.data}")
////                            binding.tvNombreUsuario.text = document.getString("nombre")
////                            Glide.with(this).load(document.getString("foto")).into(binding.imgBtnPerfilUsuario)
//                            ("${document.getString("nombre_empresa")} ")?.let { it1 ->
//                                modelFavorito?.nombre_empresa(
//                                    it1
//                                )
//                            }
//                            ("${document.getString("pais")}")?.let { it1 ->
//                                modelFavorito?.pais(
//                                    it1
//                                )
//                            }
//                            ("${document.getString("descripcion")}")?.let { it1 ->
//                                modelFavorito?.descripcion(
//                                    it1
//                                )
//                            }
//                            ("${document.getString("habilidades")}")?.let { it1 ->
//                                modelFavorito?.habilidades(
//                                    it1
//                                )
//                            }
//                            ("${document.getString("experiencia")}")?.let { it1 ->
//                                modelFavorito?.experiencia(
//                                    it1
//                                )
//                            }
//                            ("${document.getString("vacantes")}")?.let { it1 ->
//                                modelFavorito?.vacantes(
//                                    it1
//                                )
//                            }
//                            ("${document.getString("titulo")}")?.let { it1 ->
//                                modelFavorito?.titulo(
//                                    it1
//                                )
//                            }
//                            document.getString("foto")?.let { it1 -> modelFavorito?.foto(it1) }
////                            modelPerfil?.nombre(document.getString("nombre"))
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.w(TAG, "Error en UserActivity para favoritos: ", exception)
//                    }
//            }
//
//
//        }
//        -----------------------------------------------------------------------------
    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController = this.findNavController(R.id.MyNavHostFragment3)
        return NavController.navigateUp()
    }

    //    cuando le atras se salga de la app
    private val INTERVALO = 2000 //2 segundos para salir
    private var tiempoPrimerClick: Long = 0
    override fun onBackPressed() {
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()) {
//            super.onBackPressed()
            finishAffinity()

            return
        } else {
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show()
        }
        tiempoPrimerClick = System.currentTimeMillis()
    }


    //    viewmodel para pasar los datos de la pantalla_24 a la pantalla_5
    class Communicator : ViewModel() {

        val _titulo = MutableLiveData<Any>()
        val _foto = MutableLiveData<Any>()
        val _nombre_empresa = MutableLiveData<Any>()
        val _fecha = MutableLiveData<Any>()

        val _pais = MutableLiveData<Any>()
        val _descripcion = MutableLiveData<Any>()
        val _habilidades = MutableLiveData<Any>()
        val _experiencia = MutableLiveData<Any>()
        val _vacantes = MutableLiveData<Any>()
        val _correo = MutableLiveData<Any>()
        val _id = MutableLiveData<Any>()


        fun titulo(msg: kotlin.String) {
            _titulo.setValue(msg)

        }

        fun foto(msg: kotlin.String) {
            _foto.setValue(msg)

        }

        fun nombre_empresa(msg: kotlin.String) {
            _nombre_empresa.setValue(msg)

        }

        fun fecha(msg: String) {
            _fecha.setValue(msg)

        }
        fun pais(msg: String) {
            _pais.setValue(msg)

        }


        fun descripcion(msg: kotlin.String) {
            _descripcion.setValue(msg)

        }

        fun habilidades(msg: kotlin.String) {
            _habilidades.setValue(msg)

        }

        fun experiencia(msg: kotlin.String) {
            _experiencia.setValue(msg)

        }

        fun vacantes(msg: kotlin.String) {
            _vacantes.setValue(msg)

        }

        fun correo(msg: kotlin.String) {
            _correo.setValue(msg)

        }

        fun id(msg: kotlin.String) {
            _id.setValue(msg)

        }
    }

    //    Viewmodel para pasar los datos a la pantalla_19 y pantalla_30
    class getPerfil : ViewModel() {


        val _nombre = MutableLiveData<Any>()
        val _apellido = MutableLiveData<Any>()
        val _correo = MutableLiveData<Any>()
        val _pais = MutableLiveData<Any>()
        val _descripcion = MutableLiveData<Any>()
        val _foto = MutableLiveData<Any>()


        fun nombre(msg: kotlin.String) {
            _nombre.setValue(msg)

        }

        fun apellido(msg: kotlin.String) {
            _apellido.setValue(msg)

        }

        fun correo(msg: kotlin.String) {
            _correo.setValue(msg)

        }

        fun foto(msg: kotlin.String) {
            _foto.setValue(msg)

        }

        fun pais(msg: kotlin.String) {
            _pais.setValue(msg)

        }

        fun descripcion(msg: kotlin.String) {
            _descripcion.setValue(msg)

        }


    }


}
