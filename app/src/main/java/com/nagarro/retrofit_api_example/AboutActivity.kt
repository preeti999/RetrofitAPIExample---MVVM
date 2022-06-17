package com.nagarro.retrofit_api_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

//        val actionBar= supportActionBar

//        if(actionBar!=null){
//            actionBar.title="About Activity"
//        }

        //Best practice to reduce single if statement
//        actionBar!!.title="About Activity"
//        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}