package com.wilderpereira.multiplatform.smsserverapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        task.addOnSuccessListener { _ ->
            Log.d("CodeActivity", "Sms listener started!")
            listenSms()
        }

        task.addOnFailureListener { e ->
            Log.e("CodeActivity", "Failed to start sms retriever: ${e.message}")
            startSmsRetriever()
        }
    }

    private fun listenSms() {
        SmsReceiver.bindListener(object : SmsListener {
            override fun onSuccess(code: String) {
                Toast.makeText(this@MainActivity, code, Toast.LENGTH_SHORT).show()
                //Starting the listener again so it can receive more messages
                startSmsRetriever()
            }

            override fun onError() {
                Toast.makeText(this@MainActivity, "Error retrieving message", Toast.LENGTH_SHORT).show()
                startSmsRetriever()
            }
        })
    }

}
