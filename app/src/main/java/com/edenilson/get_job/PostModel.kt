//Clase para que funcione el recyclerview de la pantlla 4

package com.edenilson.get_job

import android.database.MatrixCursor
import android.opengl.Matrix
import com.google.firebase.database.collection.ArraySortedMap
import com.google.firebase.firestore.core.ArrayContainsAnyFilter
import com.google.protobuf.LazyStringArrayList
import java.sql.Timestamp
import java.util.*
import kotlin.collections.HashMap

class PostModel(
    val foto: String = "",
    val nombre_empresa: String = "",
//    val fecha_publicacion: com.google.firebase.Timestamp ,
    val pais: String = "",
    val descripcion: String = "",
    val habilidades: String = "",
    val experiencia: String = "",


    val correo: String = "",

    val titulo: String = "",
    val vacantes: String = "",
    val id: String = "",
    val sp_experiencia:String =""
//    ,
//
//    val aplicados :ArrayList<String>




)
