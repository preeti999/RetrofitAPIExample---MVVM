package com.nagarro.retrofit_api_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nagarro.retrofit_api_example.databinding.ActivityDecriptionBinding
import kotlinx.android.synthetic.main.item_main.*

class DecriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: com.nagarro.retrofit_api_example.databinding.ActivityDecriptionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_decription)


        val intent = intent
        val id: Int = intent.extras!!.getInt("value")


        val myViewModel = ViewModelProvider(this)[DecriptionViewModel::class.java]

        myViewModel.makeDectriptionAPICall(id)
        myViewModel.liveDataList?.observe(this) {
            Log.d("Description", it.toString())
            binding.item = it
        }
        Log.d("Description", id.toString())
    }
}