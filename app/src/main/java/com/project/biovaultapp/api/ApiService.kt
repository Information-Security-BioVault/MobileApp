package com.project.biovaultapp.api

import android.view.PixelCopy.Request
import com.project.biovaultapp.api.model.RequestModel
import com.project.biovaultapp.api.model.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // 인증 확인
    @POST("/api/request_authority")
    fun check(
        @Body parameters: RequestModel
    ): Call<ResponseModel>
}