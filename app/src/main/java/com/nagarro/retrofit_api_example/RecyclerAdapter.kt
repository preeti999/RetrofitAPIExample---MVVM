package com.nagarro.retrofit_api_example

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.RecyclerView
import com.nagarro.retrofit_api_example.databinding.ItemMainBinding
import kotlin.random.Random

interface AdapterCallback {
    fun onClickItem(id: Int)
}


class RecyclerAdapter(
    var context: Context,
    var dataItems: List<JsonDataItem>,
    var callback: AdapterCallback,
) :

    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listBinding = ItemMainBinding.inflate(inflater, parent, false)
        return ViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(dataItems[position])
        Log.d("DATARA", "$dataItems")
        holder.itemView.setOnClickListener {
            callback.onClickItem(dataItems[position].id)
        }


    }

    override fun getItemCount(): Int {
        return dataItems.size;
    }


    inner class ViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setItem(dataItem: JsonDataItem) {
            binding.item = dataItem
        }
    }
}


