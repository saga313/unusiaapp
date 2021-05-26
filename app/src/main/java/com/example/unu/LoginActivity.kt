package com.example.unu

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"

    //global variables
    private var email: String? = null
    private var password: String? = null

    //UI elements
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mProgressBar: ProgressDialog? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialise()
        }

    private fun initialise(){

        var url = "https://unusia-f183f-default-rtdb.firebaseio.com"
        mDatabase = FirebaseDatabase.getInstance(url)
        print("hello");
        mDatabaseReference = mDatabase!!.reference!!.child("users")
        mAuth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.MasukanUser) as EditText
        etPassword = findViewById(R.id.MasukanPassword) as EditText
        btnLogin = findViewById(R.id.btnlogin) as Button
        mProgressBar = ProgressDialog(this)
        btnLogin!!.setOnClickListener { loginUser() }
    }

    private fun updateUI() {
        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun loginUser(){
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mProgressBar!!.setMessage("Registering User...")
            mProgressBar!!.show()

            Log.d(TAG, "Logging in user.")

            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->

                        mProgressBar!!.hide()

                        if (task.isSuccessful) {
                            // Sign in success, update UI with signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            updateUI()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(this@LoginActivity, "Username atau Password Salah, Coba lagi!!",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "Masukan Username dan Password", Toast.LENGTH_SHORT).show()
        }
    }

    }

