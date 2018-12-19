package com.uvezem.data

import android.util.Base64
import com.uvezem.features.network.ApiRetrofit
import com.uvezem.model.UserApp
import io.reactivex.Single

class LoginRepository constructor(private val apiRetrofit: ApiRetrofit) {

    fun loginUser(login: String, pass: String): Single<UserApp> {
        val byteArray = "$login:$pass".toByteArray()
        val base64String = Base64.encodeToString(byteArray, Base64.NO_WRAP)
        return apiRetrofit.loginUser("Basic $base64String")
    }
}