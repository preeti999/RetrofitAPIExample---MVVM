package com.nagarro.retrofit_api_example

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var liveDataList: MutableLiveData<List<JsonDataItem>?> = MutableLiveData()
    var map: MutableLiveData<Map<String, String>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<JsonDataItem>?> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(ApiInterface::class.java)
        val call = retroService.getApiResponse()
        call.enqueue(object : Callback<JsonData> {
            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<JsonData>,
                response: Response<JsonData>
            ) {
                val result = response.body()
                Log.d("DATA","$result")
//                val reverseList = reverseList(result)
                val changeList = addedDescriptionText(result)
                liveDataList.postValue(changeList)
//                showMapResponse(result)
            }
        })
    }


    fun reverseList(data: JsonData?): List<JsonDataItem> {
        val dataList = data as? List<JsonDataItem>
        var reverseList = listOf<JsonDataItem>()
        dataList?.let { list ->
            reverseList = list.reversed()
        }
        return reverseList
    }

    fun addedDescriptionText(data: JsonData?): List<JsonDataItem> {
        val dataList = data as? List<JsonDataItem>
        return dataList?.let { list ->
            list.map { item ->
                item.title = "Description : " + item.title
                item
            }
        } ?: run {
            emptyList()
        }
    }


}