package com.example.razorpayintegration.repository

import com.example.razorpayintegration.repository.model.request.RazorpayOrderRequest
import com.example.razorpayintegration.repository.model.request.VerifyPaymentRequest
import com.example.razorpayintegration.repository.model.response.RazorpayOrderResponse
import com.example.razorpayintegration.repository.model.response.VerifyPaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("payment/v1.0/createRazorPayOrder")
    suspend fun razorpayOrderRequest(@Body request: RazorpayOrderRequest): Response<RazorpayOrderResponse>

    @POST("payment/v1.0/verifyPayment")
    suspend fun verifyPayment(@Body request: VerifyPaymentRequest): Response<VerifyPaymentResponse>

}