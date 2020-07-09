package com.edenilson.get_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Timestamp

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val NavController = this.findNavController(R.id.MyNavHostFragment3)
        NavigationUI.setupActionBarWithNavController(this, NavController)



        findViewById<BottomNavigationView>(R.id.bottomNav)
            .setupWithNavController(NavController)

//        llevo la informacion al fragmento


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
//        val _pais = MutableLiveData<Any>()
        val _descripcion = MutableLiveData<Any>()
        val _habilidades = MutableLiveData<Any>()
        val _experiencia = MutableLiveData<Any>()
        val _vacantes = MutableLiveData<Any>()


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
//        fun pais(msg: kotlin.String) {
//            _pais.setValue(msg)
//
//        }
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
    }

}
