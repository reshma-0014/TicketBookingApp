package com.mycode.ticketbookingapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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


        rootView.edit.setOnClickListener {
            activity?.let {
                val intent = Intent(it, EditProfile::class.java)
                it.startActivity(intent)
            }
        }

        // return rootView

        rootView.settings.setOnClickListener {
            activity?.let {
                val intent = Intent(it, SettingsActivity::class.java)
                it.startActivity(intent)
            }
        }
       // return rootView


        activity?.runOnUiThread {
            val ref1 = FirebaseDatabase.getInstance().getReference("/User")
            ref1.addListenerForSingleValueEvent(object : ValueEventListener {
                @RequiresApi(Build.VERSION_CODES.P)
                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        val user = it.getValue(Users::class.java)
                        if (user != null && user.uid == FirebaseAuth.getInstance().uid) {
                            if (user.profilepic!="") {
                                displayname.setText(user.username)
                                Picasso.with(context).load(user.profilepic).into(userdp1)
                                userdp.alpha = 0f
                                loading_spinner.visibility = View.GONE
                            } else {
                                displayname.setText(user.username)
                                loading_spinner.visibility = View.GONE
                            }

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }




            rootView.logout.setOnClickListener {
                activity?.let {
                    Firebase.auth.signOut()
                    val intent = Intent(it, Login::class.java)
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