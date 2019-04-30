package com.wilderpereira.multiplatform.smsserverapp

interface SmsListener {
    fun onSuccess(code: String)
    fun onError()
}