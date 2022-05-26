package com.xuancanhit.templatefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        database.child("Ho Ten").setValue("Hoang Anh")


        writeNewUser("3", "Canh2", "xuancanhit99@gmail.com")

        //database.child("users").child("2").child("Canh1").setValue("A")

//        val readWriteMap = hashMapOf("Name" to 1, "Gender" to 2)
//        val snapshot: Map<String, Int> = HashMap(readWriteMap)
//        database.child("Student").setValue(snapshot)

        //val user = User("Cuong", "cuong @gmail.com ")
        //database.child("NguoiDung").push().setValue(user)




    }

    private fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)

        //bat su kien kiem tra write database
        database.child("users").child(userId)
            .setValue(user, DatabaseReference.CompletionListener { error, ref ->
                if (error == null)
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Err when save", Toast.LENGTH_SHORT).show()

            })
    }

}