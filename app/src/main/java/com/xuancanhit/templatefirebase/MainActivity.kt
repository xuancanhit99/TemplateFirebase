package com.xuancanhit.templatefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Write a message to the database
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//        myRef.setValue("Hello, World!")


        database = Firebase.database.reference

        //writeNewUser("1", "Canh1", "xuancanhit99@gmail.com")

        //database.child("users").child("1").child("Canh1").setValue("A")

//        val readWriteMap = hashMapOf("Name" to 1, "Gender" to 2)
//        val snapshot: Map<String, Int> = HashMap(readWriteMap)
//        database.child("Student").setValue(snapshot)

         val user = User("Cuong","cuong @gmail.com ")
        database.child("NguoiDung").push().setValue(user)


        //Bat su kien hoan thanh


    }

    private fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)

        database.child("users").child(userId).setValue(user)
    }

}