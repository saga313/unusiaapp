package com.example.unu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {
    private val TAG = "DashboardActivity"
    private var btnLogout: Button? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initialise()
    }

    private fun initialise(){
        mAuth = FirebaseAuth.getInstance()
        btnLogout= findViewById(R.id.btnLogout) as Button
        btnLogout!!.setOnClickListener{logoutUser()}
    }

    private fun logoutUser(){
        mAuth!!.signOut()
        val intent = Intent(this@DashboardActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}