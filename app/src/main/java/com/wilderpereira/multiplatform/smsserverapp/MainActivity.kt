package com.wilderpereira.multiplatform.smsserverapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startSmsRetriever()
    }


    private fun startSmsRetriever() {
        val client = SmsRetriever.getClient(this)

        val task = client.startSmsRetriever()

        task.addOnSuccessListener {
            Log.d("MainActivity", "Sms listener started!")
            SmsReceiver.bindListener(RetryListener(this@MainActivity) {startSmsRetriever()})
        }

        task.addOnFailureListener { e ->
            Log.e("MainActivity", "Failed to start sms retriever: ${e.message}")
            startSmsRetriever()
        }
    }


}
