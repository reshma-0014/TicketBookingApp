package com.mycode.ticketbookingapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.profile.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [userprofile.newInstance] factory method to
 * create an instance of this fragment.
 */
class userprofile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.profile, container, false)
        val uid=FirebaseAuth.getInstance().uid



                val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")

            ref1.addListenerForSingleValueEvent(object : ValueEventListener {
                @RequiresApi(Build.VERSION_CODES.P)
                override fun onDataChange(p0: DataSnapshot) {
                    val user=p0.getValue(Users::class.java)
                        if (user != null) {
                            if (user.profilepic!="") {
                                displayname.setText(user.username)
                                Picasso.with(context).load(user.profilepic).into(userdp2)
                            } else {
                                displayname.setText(user.username)
                            }
                            loading_spinner1.visibility = View.GONE
                        }
                    }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        val ref2 = FirebaseDatabase.getInstance().getReference("/User/")
            ref2.addChildEventListener(object : ChildEventListener {
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    snapshot.children.forEach {
                        val user = snapshot.getValue(Users::class.java)
                        if (user != null && user.uid == FirebaseAuth.getInstance().uid) {
                            if (user.profilepic != "") {
                                displayname.setText(user.username)
                                Picasso.with(context).load(user.profilepic).into(userdp2)


                            } else {
                                displayname.setText(user.username)
                            }
                            loading_spinner1.visibility = View.GONE
                        }
                    }
                }

                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        rootView.edit.setOnClickListener {
            activity?.let {
                val intent = Intent(it, EditProfile::class.java)
                it.startActivity(intent)
            }
        }

            rootView.logout.setOnClickListener {
                activity?.let {
                    Firebase.auth.signOut()
                    val intent = Intent(it, Welcome::class.java)
//                    intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    it.startActivity(intent)
                    it.finish()
                }
            }
            return rootView
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment userprofile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            userprofile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}





