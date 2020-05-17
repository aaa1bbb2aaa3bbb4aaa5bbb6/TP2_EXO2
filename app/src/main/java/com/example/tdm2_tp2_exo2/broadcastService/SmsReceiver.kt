package com.example.mailnotifictaion

import android.annotation.TargetApi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import android.os.Build

import android.telephony.gsm.SmsMessage
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startForegroundService


public class SmsReceiver:BroadcastReceiver() {


    val NOTIFICATION_ID = "notification-id"
    val NOTIFICATION = "notification"
    @RequiresApi(Build.VERSION_CODES.O)
    @TargetApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent?) {
        val extra = intent!!.extras
        if (extra != null) {
            val sms = extra.get("pdus") as Array<Any>
            for (i in sms.indices) {
                val format = extra.getString("format")
                val smsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                } else {
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }
                val phonenumber = smsMessage.originatingAddress
                val text = smsMessage.messageBody.toString()
                Log.d("Sonthing ", "I just think that this is the receiver ")
                Toast.makeText(context, "phone number $phonenumber \n message text : $text", Toast.LENGTH_LONG)
                    .show()
            }
            val intent = Intent(context,MyService::class.java)
            startForegroundService(context, intent)

        }
    }
}






