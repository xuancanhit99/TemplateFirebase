package com.xuancanhit.retrievedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database:DatabaseReference
    private lateinit var myTv: TextView
    private lateinit var listView: ListView
    private lateinit var listPhuongTien: ArrayList<String>
    private var arrayAdapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPhuongTien = ArrayList<String>()

        myTv = findViewById(R.id.my_tv)
        listView = findViewById(R.id.list_view)
        arrayAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, listPhuongTien)
        listView.adapter = arrayAdapter

        database = Firebase.database.reference



        //database.child("Ho Ten").push().setValue("Xuan Bach")

        //Doc note nhieu con khi khong dung doi tuong (sd append de them tung cai)
        database.child("Ho Ten").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                 myTv.append(snapshot.value.toString() + "\n")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        //val phuongtien = PhuongTien("xe khách", 10 )
        //Push object
        //database.child("PhuongTien").push().setValue(phuongtien)

        //Hien thi tat ca doi tuong phuong tien da push
        database.child("PhuongTien").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val ptien = snapshot.getValue(PhuongTien::class.java)
                if (ptien != null) {

                    listPhuongTien.add(ptien.ten + "-" +ptien.banh + " bánh")
                    arrayAdapter!!.notifyDataSetChanged()
                    //Toast.makeText(this@MainActivity, ptien.ten, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}