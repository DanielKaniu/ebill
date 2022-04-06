package com.example.mutall_rental

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mutall_rental.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //
    val permissionRequest = 101
    //
    var inboxMsg: JSONArray = JSONArray()
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        //The instance of the binding object
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //
        //Set the activity content from a layout resource. The resource will be inflated,
        //adding all top-level views to the activity.
        setContentView(binding.root)
        //
        //Open the activity through which to send an sms.
        binding.sendSms.setOnClickListener {
            //
            //Open a new activity to send the message.
            val intent = Intent(this, send_sms::class.java)
            startActivity(intent)
        }
        //
        //Open the activity through which to inbox messages from KPLC.
        binding.viewSms.setOnClickListener {
            //
            //Open a new activity to view messages from KPLc.
            //
            //View inbox messages from Kenya Power.
            //
            //The variable we check to see if read_sms permission is granted or not.
            val permissionCheck =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
            //
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //
                //Get the inbox messages.
                //
                //Open a new activity to send the message.
                val intent = Intent(this, view_inbox::class.java)
                startActivity(intent)
            }
            //
            //If permission is not granted, prompt the user for permission.
            else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_SMS),
                    permissionRequest
                )
            }


        }
    }
}