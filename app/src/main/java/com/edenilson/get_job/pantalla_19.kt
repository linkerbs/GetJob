package com.edenilson.get_job

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.edenilson.get_job.databinding.FragmentPantalla19Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_pantalla_19.*
import kotlinx.android.synthetic.main.fragment_pantalla_9.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class pantalla_19 : Fragment() {
    //    Esto es para utilizarlo con el modelView con la data del usuario
    private var modelPerfil: UserActivity.getPerfil? = null
    private lateinit var mAuth: FirebaseAuth
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var selectedPhoto: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhoto = data.data

            Toast.makeText(
                activity,selectedPhoto.toString(),
                Toast.LENGTH_SHORT
            ).show()
            btPdf.text = "Seleccionado"
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla19Binding>(
            inflater, R.layout.fragment_pantalla_19
            , container, false
        )

        (activity as UserActivity).supportActionBar?.title = ("Perfil")



        binding.btnModificarPerfilUsuario.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pantalla_19_to_pantalla_30)
        }

        binding.btCerrar.setOnClickListener { view: View ->

           /* Firebase.auth.signOut()
            val intent = Intent(activity, MainActivity::class.java)
            activity!!.startActivity(intent)*/
            pdfUpload()
        }

        //        Esto es para utilizarlo con modelView
        modelPerfil = ViewModelProviders.of(activity!!).get(UserActivity.getPerfil::class.java)


//        los sampo a los Input
//        Aqui sumo el nombre con el appellido
//        Aqui valido que no me vengan los campos null y si vienen les seteo esa wea

        binding.tvNombreUsuario?.text =
            modelPerfil!!._nombre.value.toString() + modelPerfil!!._apellido.value.toString()

//Aqui valido lo de los campos nulos
        if (modelPerfil!!._descripcion.value.toString() == "null" || modelPerfil!!._descripcion.value.toString() == "") {
            binding.tvDescripcionCompletaUsuario.setText("Edita tu perfil para ingresa una breve descripcion de tu persona .")
        } else {
            binding.tvDescripcionCompletaUsuario.text = modelPerfil!!._descripcion.value.toString()

        }
        if (modelPerfil!!._pais.value.toString() == "null" || modelPerfil!!._pais.value.toString() == "") {
            binding.tvPais.setText("Edita tu perfil para ingresar el nombre del pais de nacimiento")
        } else {
            binding.tvPais.text = modelPerfil!!._pais.value.toString()


        }
//        Seteo la imagen
        val imagen = modelPerfil!!._foto.value.toString()
        binding.imgFotoPerfil.let { Glide.with(this).load(imagen).into(it) }

        binding.btPdf.setOnClickListener { view: View ->
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(intent, 0)



        }





        return binding.root


    }


   private fun pdfUpload() {


       var devolver: String = ""
       val db = FirebaseFirestore.getInstance()

       val user = FirebaseAuth.getInstance().currentUser
       user?.let {
           val correo = user.email
           val ref = FirebaseStorage.getInstance().getReference("/cv/$correo")

           ref.putFile(selectedPhoto!!).addOnSuccessListener { it ->
               Log.d("Registro", "Se subio la imagen: ${it.metadata?.path}")

               ref.downloadUrl.addOnSuccessListener {
                   val doc = db.collection("usuarios").document(correo!!)
                   doc.update("cv", it)
                   .addOnSuccessListener { }
                   .addOnFailureListener {  }



               }
           }

       }
   }

}
