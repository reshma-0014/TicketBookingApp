package com.mycode.ticketbookingapp


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.profile.*
import java.util.*


class EditProfile : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editprofile)
        database= FirebaseDatabase.getInstance()
        supportActionBar?.title = "Edit Profile"
        runOnUiThread {
            val ref1 = FirebaseDatabase.getInstance().getReference("/User")
            ref1.addListenerForSingleValueEvent(object : ValueEventListener {

                @RequiresApi(Build.VERSION_CODES.P)
                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        val user = it.getValue(Users::class.java)
                        if (user != null && user.uid == FirebaseAuth.getInstance().uid) {
                            if(user.profilepic!="") {
                                findViewById<EditText>(R.id.name).setText(user.username)
                                findViewById<EditText>(R.id.email).setText(user.email)
                                Picasso.with(this@EditProfile).load(user.profilepic)
                                    .into(findViewById<CircleImageView>(R.id.userdp1))
                                findViewById<EditText>(R.id.userdp).alpha = 0f
                            }else{
                                findViewById<EditText>(R.id.name).setText(user.username)
                                findViewById<EditText>(R.id.email).setText(user.email)
                            }
                        }

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

        findViewById<EditText>(R.id.userdp).setShowSoftInputOnFocus(false)
        findViewById<Button>(R.id.savechanges).setOnClickListener{
            savechanges()
        }

        findViewById<CircleImageView>(R.id.userdp1).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }
    }


        var selectedPhotoUri: Uri?=null
    @RequiresApi(Build.VERSION_CODES.P)
    //If the image is selected to start the activity of gallery and assigning it to @selectedPhotoUri
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RegisterActivity", "Photo was selected")
            selectedPhotoUri = data.data

            Picasso.with(this@EditProfile).load(selectedPhotoUri)
                .into(findViewById<CircleImageView>(R.id.userdp1))
            findViewById<EditText>(R.id.userdp).alpha = 0f //to no inflate image view

          uploadImageToFirebaseStorage()
        }
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d("RegisterActivitiy", "File Location:$it")
                }
            }

    }

    private fun savechanges(){
        val ref1 = FirebaseDatabase.getInstance().getReference("/User")
        ref1.addListenerForSingleValueEvent(object : ValueEventListener {

            @RequiresApi(Build.VERSION_CODES.P)
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val user = it.getValue(Users::class.java)
                    if (user != null && user.uid == FirebaseAuth.getInstance().uid) {

//                        user.username=findViewById<EditText>(R.id.name).text.toString()
//                        user.email=findViewById<EditText>(R.id.email).text.toString() :(

//                        database.getReference().child("/Users/uid/profilepic").setValue(findViewById<EditText>(R.id.name).text.toString()) :(
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }





}