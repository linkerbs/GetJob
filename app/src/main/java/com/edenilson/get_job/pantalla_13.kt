package com.edenilson.get_job

import android.app.*
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.edenilson.get_job.databinding.FragmentPantalla13Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_pantalla_31.*
import kotlin.math.exp

/**
 * A simple [Fragment] subclass.
 */
class pantalla_13 : Fragment() {

    lateinit var notificacionManager: NotificationManager
    lateinit var notificacionChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.edenilson.get_job"
    private val description = "Test notification"

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPantalla13Binding>(
            inflater, R.layout.fragment_pantalla_13
            , container, false
        )
        notificacionManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager







        (activity as CompanyActivity).supportActionBar?.title = ("Publicar oferta")

        mAuth = FirebaseAuth.getInstance();
        val db = FirebaseFirestore.getInstance()



        binding.btnGuardar?.setOnClickListener { view: View ->

            var titulo: String = binding.tvTituloOferta?.text.toString()
            var descripcion: String = binding.tvDescripcionOferta?.text.toString()
            var habilidades: String = binding.tvHabilidades?.text.toString()
            var experiencias: String = binding.tvExperiencias?.text.toString()
            var vacantes: String = binding.etVacante?.text.toString()



            if (titulo.isEmpty() || descripcion.isEmpty() || habilidades.isEmpty() || experiencias.isEmpty() || vacantes.isEmpty()) {

        }else
            {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val name = user.email

                    db.collection("usuarios")
                        .whereEqualTo("correo", name)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                var nombre: String = document.getString("nombre")!!
                                var foto: String = document.getString("foto")!!
                                var correo: String = document.getString("correo")!!

                                val oferta: MutableMap<String, Any> =
                                    HashMap()
                                oferta["titulo"] = titulo
                                oferta["descripcion"] = descripcion
                                oferta["experiencia"] = experiencias
                                oferta["habilidades"] = habilidades
                                oferta["vacantes"] = vacantes
                                oferta["fecha_publicacion"] = java.util.Calendar.getInstance().time
                                oferta["nombre_empresa"] = nombre
                                oferta["foto"] = foto
                                oferta["correo"] = correo

                                db.collection("ofertas")
                                    .add(oferta)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(
                                            ContentValues.TAG,
                                            "Se agrego el documento con ID " + documentReference.id
                                        )
                                        view?.findNavController()
                                            .navigate(R.id.action_pantalla_13_to_fragment_pantalla_11)
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            ContentValues.TAG,
                                            "C mamo algo",
                                            e
                                        )
                                    }

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }


                }


            }
            val intent = Intent(activity, LauncherActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(activity,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val contentView = RemoteViews(activity!!.packageName,R.layout.notificacion_layout)
            contentView.setTextViewText(R.id.tv_title,"OFERTA DE EMPLEO")
            contentView.setTextViewText(R.id.tv_content,"Se ha publicado una nueva oferta")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificacionChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificacionChannel.enableLights(true)
                notificacionChannel.lightColor = Color.BLUE
                notificacionChannel.enableVibration(true)
                notificacionManager.createNotificationChannel(notificacionChannel)

                builder = Notification.Builder(activity,channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.ic_notifications_black)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher_round))
                    .setContentIntent(pendingIntent)
            }else{

                builder = Notification.Builder(activity)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.ic_notifications_black)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher_round))
                    .setContentIntent(pendingIntent)
            }
            notificacionManager.notify(1234,builder.build())

            }

        return binding.root
    }


}

