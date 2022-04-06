package com.example.mutall_rental

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mutall_rental.databinding.ActivitySendSmsBinding

class send_sms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        val permissionRequest = 101
        //
        //Create a binding instance.
        val binding = ActivitySendSmsBinding.inflate(layoutInflater)
        //
        //Set the activity content from a layout resource. The resource will be inflated,
        //adding all top-level views to the activity.
        setContentView(binding.root)
        //
        //Send the message upon clicking the send button.
        binding.send.setOnClickListener{
            //
            //
            val permissionCheck =
                ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //
                //Get the phone number as a string.
                val phone = binding.address.text.toString()
                //
                //Get the message as a string.
                val msg = binding.msg.text.toString()
                //
                //Get the SMS manager API.
                val smsManager = SmsManager.getDefault() as SmsManager
                //
                //Ensure we have a message to send and an address to send the message to.
                if(!(isEmpty(phone) || isEmpty(msg))){
                    //
                    //Send the message.
                    smsManager.sendTextMessage(phone, null, msg, null, null)
                    //
                    //Message to show the message is being sent.
                    Toast.makeText(this, "Sending SMS", Toast.LENGTH_SHORT).show()
                }
                //
                //Otherwise ask the user to input them.
                else{
                    //
                    //Message to show the message is being sent.
                    Toast.makeText(this, "Phone address/message missing", Toast.LENGTH_SHORT).show()
                }
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.SEND_SMS),
                    permissionRequest
                )
            }
        }
    }
}