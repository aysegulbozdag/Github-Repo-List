package com.example.simpleexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleexample.model.RecyclerListModel
import com.example.simpleexample.network.RetroInstance
import com.example.simpleexample.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    lateinit var recyclerListLiveData: MutableLiveData<RecyclerListModel>

    init {
        recyclerListLiveData = MutableLiveData()
    }


    fun getRecyclerListObserver(): MutableLiveData<RecyclerListModel> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {

            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("ny")
            recyclerListLiveData.postValue(response)

        }
    }
}