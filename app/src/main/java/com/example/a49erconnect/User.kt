package com.example.a49erconnect

import android.provider.ContactsContract.CommonDataKinds.Email

class User {

    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var uncc_number: String? = null


    constructor(){}


    constructor(name: String?,uncc_number: String?, email: String?, uid: String){
        this.email = email
        this.uncc_number = uncc_number
        this.name = name
        this.uid = uid
    }


}