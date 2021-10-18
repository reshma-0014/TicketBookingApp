package com.mycode.ticketbookingapp

class Users {
    var email:String
    var username:String?
    var profilepic:String



    constructor(
        email: String,
        username: String?,
        profilepic: String

       // mail: String,
        //password: String,

    ) {
        this.email = email
        this.username = username
        this.profilepic = profilepic

        //this.mail = mail
        //this.password = password

    }

}