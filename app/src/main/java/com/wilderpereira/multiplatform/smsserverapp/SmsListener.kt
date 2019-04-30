package com.wilderpereira.multiplatform.smsserverapp

import android.content.Context
import android.widget.Toast

interface SmsListener {
    fun onSuccess(code: String)
    fun onError()
}

class RetryListener(val context: Context, val function: () -> Unit) : SmsListener {
    override fun onSuccess(code: String) {
        Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
        //Starting the listener again so it can receive more messages
        function.invoke()
    }

    override fun onError() {
        Toast.makeText(context, "Error retrieving message", Toast.LENGTH_SHORT).show()
        function.invoke()
    }
}