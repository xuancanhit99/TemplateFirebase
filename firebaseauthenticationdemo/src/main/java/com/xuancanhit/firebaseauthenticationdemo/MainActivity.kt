package com.xuancanhit.firebaseauthenticationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var buttonRegister: Button

    private lateinit var edtEmailLogin:EditText
    private lateinit var edtPasswordLogin:EditText
    private lateinit var buttonLogin: Button


    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtEmail = findViewById(R.id.edtEmailAddress)
        edtPassword = findViewById(R.id.edtPassword)
        buttonRegister = findViewById(R.id.button)

        edtEmailLogin = findViewById(R.id.edtEmailAddressLogin)
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin)
        buttonLogin = findViewById(R.id.buttonLogin)


        auth = Firebase.auth


        buttonRegister.setOnClickListener{
            registerAcc()
        }

        buttonLogin.setOnClickListener {
            loginAcc()
        }

    }

    private fun loginAcc() {
        val email = edtEmailLogin.text.toString()
        val password = edtPasswordLogin.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()

                    //val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun registerAcc() {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
//                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }


}