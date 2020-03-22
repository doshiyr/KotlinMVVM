package com.yashdoshi.kotlinmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yashdoshi.kotlinmvvm.R
import com.yashdoshi.kotlinmvvm.retrofit.models.BoredActivity
import com.yashdoshi.kotlinmvvm.repository.BoredRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoredViewModel(val context: Application) : AndroidViewModel(context) {

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean> get() = _isLoading

    private val _boredActivity : MutableLiveData<BoredActivity> = MutableLiveData()
    val boredActivity : LiveData<BoredActivity> get() = _boredActivity
    
    private val _isError : MutableLiveData<String> = MutableLiveData()
    val isError: LiveData<String> get() = _isError

    val boredRepository = BoredRepository()

    init {
        getBoredActivity()
    }
    
    fun getBoredActivity(){
        _isLoading.value = true
        boredRepository.getActivity().enqueue(object : Callback<BoredActivity>{
            override fun onResponse(call: Call<BoredActivity>, response: Response<BoredActivity>) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _boredActivity.value = response.body()
                } else {
                    _isError.value = context.getString(R.string.str_something_wrong)
                }
            }
            
            override fun onFailure(call: Call<BoredActivity>, t: Throwable) {
                _isLoading.value = false
                _isError.value = context.getString(R.string.str_something_wrong)
            }
        })
    }

}