package com.example.resume.ui.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.resume.R
import com.example.resume.util.EmailValidator
import com.example.resume.util.ResumePreference

class Authenticate : AppCompatActivity() {


    private var email: EditText? = null
    private var mobileNumber: EditText? = null
    private var sharedPreference:ResumePreference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreference=ResumePreference(this)
        if (sharedPreference!!.checkVerifiedUser(getString(R.string.verified_user),false)){
            launchResumeActivity()
        }else{
            setContentView(R.layout.activity_authenticate)
            initialize()
        }
    }

    // this method is use to initialize the view content
    private fun initialize() {

        email = findViewById(R.id.email) as EditText
        mobileNumber = findViewById(R.id.mobileNumber) as EditText
        val submit = findViewById(R.id.submit) as Button

        submit.setOnClickListener {
            verifyEntry()
        }

    }

    //this metnod is used to check that the email is valid or not
    private fun verifyEntry() {

        val textEmail = email!!.text.toString()
        val textMobileNumber = mobileNumber!!.text.toString()
        if (textEmail.isNotEmpty() && textMobileNumber.isNotEmpty()) {
            if (EmailValidator.validateEmail(textEmail)) {
                sharedPreference!!.setVerifiedUser(getString(R.string.verified_user),true)
                launchResumeActivity()
            } else {
                showAlertPopup(getString(R.string.invalid_email))
            }
        } else {
            showAlertPopup(getString(R.string.empty_input_box))
        }
    }

    //this method is used to launch the main activity
    private fun launchResumeActivity(){
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //this method is used to show the alert dialogue with respect to the event
    private fun showAlertPopup(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.heading_msg))
        builder.setMessage(message)
        builder.show()
    }
}
