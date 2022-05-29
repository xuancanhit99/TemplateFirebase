package com.xuancanhit.storagefirebase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var btnSaveImg: Button
    private lateinit var ivImg: ImageView
    private lateinit var edtImgName: EditText
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSaveImg = findViewById(R.id.btnSaveImg)
        ivImg = findViewById(R.id.ivImg)
        edtImgName = findViewById(R.id.edtImgName)

        //Node goc la database - reference - tham chieu
        database = Firebase.database.reference
        storage = Firebase.storage
        val storageRef = storage.reference


        ivImg.setOnClickListener {
            openCameraActivityForResult()
        }

        btnSaveImg.setOnClickListener {

            val calendar = Calendar.getInstance()

            val mountainsRef = storageRef.child("image" + calendar.timeInMillis + ".png")

            // Get the data from an ImageView as bytes
            ivImg.isDrawingCacheEnabled = true
            ivImg.buildDrawingCache()
            val bitmap = (ivImg.drawable as BitmapDrawable).bitmap
            val byteAOS = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteAOS)
            val data = byteAOS.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                Toast.makeText(this, "Failed when upload image!", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                Toast.makeText(this, "Successfully when upload image!", Toast.LENGTH_SHORT).show()

                // Lay link file vua moi up

                uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    mountainsRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        Log.d("Link", downloadUri.toString())

                        //Tao node tren database
                        val image = MyImage(edtImgName.text.toString(), downloadUri.toString())
                        database.child("Images").push().setValue(image) { error, ref ->
                            if (error == null)
                                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                            else
                                Toast.makeText(this, "Err when save", Toast.LENGTH_SHORT).show()

                        }

                    } else {
                        // Handle failures
                        Toast.makeText(this, "Failed when create link image!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


            }
        }

    }


    private fun openCameraActivityForResult() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startForResult.launch(intent)
    }


    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                // Handle the Intent
                //do stuff here
                if (intent != null) {
                    val bitmap = intent.extras?.get("data") as Bitmap
                    ivImg.setImageBitmap(bitmap)
                }


            }
        }

}