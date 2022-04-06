package com.example.mutall_rental

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mutall_rental.databinding.ActivityViewInboxBinding


class view_inbox : AppCompatActivity() {
    //
//    ListView listView
//    //
//    ArrayList<string> smsList
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        //Create a binding instance.
        val binding = ActivityViewInboxBinding.inflate(layoutInflater)
        //
        //Set the activity content from a layout resource. The resource will be inflated,
        //adding all top-level views to the activity.
        setContentView(binding.root)
        //
        //Get the inbox messages.
        viewKPLC(binding)
    }
    //
    private fun viewKPLC(binding: ActivityViewInboxBinding) {
        //
        //
        val textView: TextView = binding.viewSms;
        //
        viewInbox(textView)
        //
        //if (viewInbox(textView) != null) {
            //
            //Get the textview element and attach the retrieved messages from the inbox.
            //textView.text = viewInbox(textView).toString();
            //
            //Make the textview scrollable.
            //binding.viewSms.movementMethod;
        //}
    }

    private fun viewInbox(textView: TextView): Any {
        //
        val sms = ArrayList<Any>()
        //
        //Prepare the inbox uri.
        val uriSms = Uri.parse("content://sms/inbox")
        //
        val cursor = contentResolver.query(
            uriSms,
            null,
            "97771",
            null,
            null
        )
        val indexBody = cursor?.getColumnIndex("body")
        val indexAddress = cursor?.getColumnIndex("address")

        while (cursor!!.moveToNext()) {
            val address =  cursor.getString(indexAddress!!)
            val body = cursor.getString(indexBody!!)
            sms.add( address + body + "\n")
        }
        return sms
    }
}