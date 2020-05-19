package com.edenilson.get_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pantalla_11)

        val NavController = this.findNavController(R.id.MyNavHostFragment2)
        NavigationUI.setupActionBarWithNavController(this, NavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController = this.findNavController(R.id.MyNavHostFragment2)
        return NavController.navigateUp()
    }
}
