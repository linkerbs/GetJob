//Clase para que funcione el recyclerview de ofertas laborales "pantalla 4 "

package com.edenilson.get_job

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
private const val TAG: String = "FirebaseRepo"

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
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d(TAG, "Desde aqui recibo las ofertas")
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
//        Log.i("FirebaseRepo","Termina de consultar las ofertas laborales")
    }


//    Obtener oferta laboral para la  empresa que lo posteo
//
    fun getEmpresa(correo: String): Task<QuerySnapshot> {
    return firebaseFirestore
            .collection("ofertas")
//            .orderBy("fecha_publicacion", Query.Direction.DESCENDING)
            .whereEqualTo("correo", correo)

            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d(TAG, "Desde aqui recibo las ofertas")
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }


    }

    //    Obtener la oferta laboral favorita o la solicitud enviada
//
    fun getFavorito(correo: String): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("ofertas")
            .whereArrayContains("usuarios", correo)
            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    Log.d(TAG, "Desde aqui recibo los favoritos")
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }

    }


    //    Obtener la oferta laboral que tienen postulantes
    fun getAplicados(correo:String): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("ofertas")
            .whereEqualTo("correo", correo)
            .whereEqualTo("estado", true)
//            .orderBy("fecha_publicacion", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "Esto son los aplicados")
                    Log.d(TAG, "${document.id} => ${document.data}")


                }




            }

    }


    //    Obtener la oferta laboral que tienen postulantes
    fun getMisAplicados(correo:String): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("ofertas")
            .whereArrayContains("aplicados", correo)
            .whereEqualTo("estado", true)
//            .orderBy("fecha_publicacion", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "Esto son los aplicados")
                    Log.d(TAG, "${document.id} => ${document.data}")


                }




            }

    }




}