package com.edenilson.get_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_11)

//  Spinner de fecha de publicacion
        // access the items of the list
        val array_fecha_publicacion = resources.getStringArray(R.array.sp_fecha_publicacion)

        // access the spinner
        val sp_fecha_publicacion = findViewById<Spinner>(R.id.sp_fecha_publicacion)
        if (sp_fecha_publicacion != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_fecha_publicacion)
            sp_fecha_publicacion.adapter = adapter


        }

//  Spinner  de Tipo de empleo
        // access the items of the list
        val array_tipo_empleo = resources.getStringArray(R.array.sp_tipo_empleo)

        // access the spinner
        val sp_tipo_empleo = findViewById<Spinner>(R.id.sp_tipo_empleo)
        if (sp_tipo_empleo != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_tipo_empleo)
            sp_tipo_empleo.adapter = adapter


        }
//  Spinner de Ordenar
        // access the items of the list
        val array_ordenar = resources.getStringArray(R.array.sp_ordenar)

        // access the spinner
        val sp_ordenar = findViewById<Spinner>(R.id.sp_ordenar)
        if (sp_ordenar != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_ordenar)
            sp_ordenar.adapter = adapter


        }

//  Spinner de Funcion laboral
        // access the items of the list
        val array_funcion_laboral = resources.getStringArray(R.array.sp_funcion_laboral)

        // access the spinner
        val sp_funcion_laboral = findViewById<Spinner>(R.id.sp_funcion_laboral)
        if (sp_funcion_laboral != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_funcion_laboral)
            sp_funcion_laboral.adapter = adapter


        }




    }
}
