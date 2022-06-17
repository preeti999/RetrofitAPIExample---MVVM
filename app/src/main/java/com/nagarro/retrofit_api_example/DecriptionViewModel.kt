package com.nagarro.retrofit_api_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DecriptionViewModel : ViewModel() {

    var liveDataList: MutableLiveData<JsonDataItem?>? = MutableLiveData()
//    var map: MutableLiveData<Map<String, String>> = MutableLiveData()


    fun makeDectriptionAPICall(id: Int) {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(ApiInterface::class.java)
        val call = retroService.getDescriptionById(id)

        call.enqueue(object : Callback<JsonDataItem> {
            override fun onFailure(call: Call<JsonDataItem>, t: Throwable) {
                liveDataList?.postValue(null)
            }

            override fun onResponse(
                call: Call<JsonDataItem>,
                response: Response<JsonDataItem>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let {
                        liveDataList?.postValue(it)
                    } ?: run {
                        liveDataList?.postValue(null)
                    }

                } else {
                    liveDataList?.postValue(null)
                }
            }
        })
    }
}

