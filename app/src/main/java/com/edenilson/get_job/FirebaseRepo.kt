//Clase para que funcione el recyclerview de ofertas laborales "pantalla 4 "

package com.edenilson.get_job
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirebaseRepo {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()




    //    firestore - Obtener informacio de las ofertas



    fun getPostList(): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("ofertas")
//            .whereEqualTo("correo",2)
            .orderBy("fecha_publicacion", Query.Direction.DESCENDING)
            .get()
//        Log.i("FirebaseRepo","Termina de consultar las ofertas laborales")
    }

//    Obtener oferta laboral para la  empresa que lo posteo
//
    fun getEmpresa(correo:String): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("ofertas")
            .whereEqualTo("correo",correo)
            .get()

    }






}