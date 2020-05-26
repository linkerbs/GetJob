package com.edenilson.get_job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class CompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        val NavController = this.findNavController(R.id.MyNavHostFragment2)
        NavigationUI.setupActionBarWithNavController(this, NavController)

        findViewById<BottomNavigationView>(R.id.bottomNav)
            .setupWithNavController(NavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val NavController = this.findNavController(R.id.MyNavHostFragment2)
        return NavController.navigateUp()
    }
}
