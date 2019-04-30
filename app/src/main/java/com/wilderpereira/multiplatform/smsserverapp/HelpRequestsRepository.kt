package com.wilderpereira.multiplatform.smsserverapp

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HelpRequestsRepository {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun saveMessage(message: String) {
        val newHelpRequestReference = database.child("help-requests").push()
        newHelpRequestReference.setValue(message)
    }

}