package com.example.kotlincoroutinesapps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UrlViewModel : ViewModel() {

    private val urlResp = MutableLiveData<String>()
    private val repository = Repository()

    fun urlRetrieve() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                delay(1000)
                var result = repository.urlRequest()
                when (result) {
                    is Result.Error -> urlResp.postValue(result.exception.message)
                    is Result.Success -> urlResp.postValue(result.data)
                }
            }
        }
    }

    fun setUrlResp() : MutableLiveData<String> {
        return urlResp
    }
}