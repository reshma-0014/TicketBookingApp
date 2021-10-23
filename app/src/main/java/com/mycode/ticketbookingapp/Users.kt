package com.mycode.ticketbookingapp


class Users {
    var email:String
    var username:String?
    var profilepic:String



    constructor(
        email: String,
        username: String?,
        profilepic: String


    ) {
        this.email = email
        this.username = username
        this.profilepic = profilepic



    }


import android.net.Uri


//
//class Users{
//    var uid:String
//    var email:String
//    var username:String
//    var profilepic:String?
//    var password:String?
//
//    // var mail:kotlin.String
//   // var password:kotlin.String
//
//
//    constructor(
//        uid:String,
//        email: String,
//        username: String,
//        profilepic: String?="",
//        password:String?=""
//
//
//    ) {
//        this.uid=uid
//        this.email = email
//        this.username = username
//        this.profilepic = profilepic
//        this.password=password
//
//
//
//    }
//
//}



class Users(val uid:String, val email:String, val username:String, var profilepic: String?= null, val password:String?=null) {
    constructor():this("","","","","")

}