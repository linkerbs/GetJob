package com.edenilson.get_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

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
    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController = this.findNavController(R.id.MyNavHostFragment3)
        return NavController.navigateUp()
    }
}
