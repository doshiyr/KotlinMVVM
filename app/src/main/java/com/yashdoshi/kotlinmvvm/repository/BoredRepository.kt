package com.yashdoshi.kotlinmvvm.repository

import com.yashdoshi.kotlinmvvm.retrofit.RestClient
import com.yashdoshi.kotlinmvvm.retrofit.models.BoredActivity
import retrofit2.Call

class BoredRepository : IBoredRepository {

    private val restClient = RestClient()

    override fun getActivity(): Call<BoredActivity> {
        return restClient.apiInterface.getActivity()
    }

}