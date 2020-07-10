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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG: String = "CompanyActivity"
class CompanyActivity : AppCompatActivity() {

    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: UserActivity.getPerfil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val NavController = this.findNavController(R.id.MyNavHostFragment2)
        NavigationUI.setupActionBarWithNavController(this, NavController)

        findViewById<BottomNavigationView>(R.id.bottomNav)
            .setupWithNavController(NavController)

        //        llevo la informacion al fragmento

        modelPerfil = ViewModelProviders.of(this).get(UserActivity.getPerfil::class.java)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val correo = user.email
            Log.d(TAG,"Usuario: ${user.uid}" )

            //    Obtengo la informacion de la oferta laboral
            if (correo != null) {

                firebaseFirestore.collection("usuarios")
                    .whereEqualTo("correo", correo)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            Log.d(TAG, "${document.id} => ${document.data}")
//                            binding.tvNombreUsuario.text = document.getString("nombre")
//                            Glide.with(this).load(document.getString("foto")).into(binding.imgBtnPerfilUsuario)
                            document.getString("nombre")?.let { it1 -> modelPerfil?.nombre(it1) }
                            document.getString("foto")?.let { it1 -> modelPerfil?.foto(it1) }
//                            modelPerfil?.nombre(document.getString("nombre"))
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents for CompanyActivity: ", exception)
                    }
            }


        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController = this.findNavController(R.id.MyNavHostFragment2)
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








    //    viewmodel para pasar los datos a la pantalla_16
        class getPerfil : ViewModel() {


            val _nombre = MutableLiveData<Any>()
            val _correo = MutableLiveData<Any>()
            val _foto = MutableLiveData<Any>()


            fun nombre(msg: kotlin.String) {
                _nombre.setValue(msg)

            }

            fun correo(msg: kotlin.String) {
                _correo.setValue(msg)

            }

            fun foto(msg: kotlin.String) {
                _foto.setValue(msg)

            }
        }




}
