package com.edenilson.get_job

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla13Binding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_pantalla_31.*

/**
 * A simple [Fragment] subclass.
 */
class pantalla_13 : Fragment() {

    lateinit var db: DocumentReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla13Binding>(
            inflater, R.layout.fragment_pantalla_13
            , container, false)

        db = FirebaseFirestore.getInstance().document("ofertas")

        val store = findNavController().navigate(R.id.button3) as Button
        store.setOnClickListener {
            view : View? -> store()
        }



        (activity as CompanyActivity).supportActionBar?.title = ("Publicar oferta")


        return binding.root
    }

    private fun store(){
        val descripcionEmpleoTxt = findNavController().navigate(R.id.d_empleo) as EditText
        val habilidadesTxt = findNavController().navigate(R.id.habilidades_descrip) as EditText
        val conocimientosTxt = findNavController().navigate(R.id.conocimiento_descripcion) as EditText
        val experienciaTxt = findNavController().navigate(R.id.editText2) as EditText
        val vacantesTxt = findNavController().navigate(R.id.et_vacantes) as EditText

        var descripcionEmpleo = descripcionEmpleoTxt.text.toString().trim()
        var habilidades = habilidadesTxt.text.toString().trim()
        var conocimientos = conocimientosTxt.text.toString().trim()
        var experiencia = experienciaTxt.text.toString().trim()
        var vacantes = vacantesTxt.text.toString().trim()

        if (!descripcionEmpleo.isEmpty() && !habilidades.isEmpty() && !conocimientos.isEmpty() && !experiencia.isEmpty() && !vacantes.isEmpty()){

            try {
                val items = HashMap<String, Any>()
                items.put("Habilidades",habilidades)
                items.put("Conocimientos",conocimientos)
                items.put("Experiencia",experiencia)
                items.put("Vacantes",vacantes)
                db.collection(descripcionEmpleo).document("Descripcion de empleo ").set(items).addOnSuccessListener {
                    void: Void? -> Toast.makeText(activity,"Exito al subir a la base de datos",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    exception : java.lang.Exception -> Toast.makeText(activity,exception.toString(),Toast.LENGTH_LONG).show()
                }

            }catch (e:Exception){
                Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(activity,"Por favor llene los campos",Toast.LENGTH_LONG).show()
        }
    }
}

