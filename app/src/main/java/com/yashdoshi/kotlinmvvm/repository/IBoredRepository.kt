package com.yashdoshi.kotlinmvvm.repository

import com.yashdoshi.kotlinmvvm.retrofit.models.BoredActivity
import retrofit2.Call

interface IBoredRepository {
    fun getActivity(): Call<BoredActivity>
}