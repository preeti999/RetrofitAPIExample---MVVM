package com.nagarro.retrofit_api_example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nagarro.retrofit_api_example.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), AdapterCallback {

    private lateinit var adapter: RecyclerAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.makeAPICall()
        with(viewModel) {
            this.getLiveDataObserver().observe(this@MainActivity) {
                if (it != null) {
                    initRecyclerView(it)
                    Log.d("DATAR", "$it")
                }
            }
        }
    }

    override fun onClickItem(id: Int) {
        launchDescriptionScreen(id)
    }

    private fun initRecyclerView(dataItems: List<JsonDataItem>) {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(this, dataItems, this) //to transfer data
        recyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> exitProcess(-1)
            R.id.about -> {
                launchAboutScreen()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun launchAboutScreen() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun launchDescriptionScreen(id: Int) {
        val intent = Intent(this, DecriptionActivity::class.java)
        intent.putExtra("value", id)
        startActivity(intent)
    }
}
