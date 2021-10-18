package com.mycode.ticketbookingapp

class Users {
    var email:String
    var username:String
    var profilepic:String? =null
    var password:String? =null

    // var mail:kotlin.String
   // var password:kotlin.String


    constructor(
        email: String,
        username: String,
        profilepic: String?,
        password:String?

       // mail: String,
        //password: String,

    ) {
        this.email = email
        this.username = username
        this.profilepic = profilepic
        this.password=password

        //this.mail = mail
        //this.password = password

    }

}