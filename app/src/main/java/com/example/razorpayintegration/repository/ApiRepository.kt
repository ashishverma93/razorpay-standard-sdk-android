package com.example.razorpayintegration.repository

import com.example.razorpayintegration.repository.model.request.RazorpayOrderRequest
import com.example.razorpayintegration.repository.model.request.VerifyPaymentRequest
import com.example.razorpayintegration.repository.model.response.RazorpayOrderResponse
import com.example.razorpayintegration.repository.model.response.VerifyPaymentResponse
import retrofit2.Response
import retrofit2.http.Body

interface ApiRepository {

    suspend fun razorpayOrderRequest(request: RazorpayOrderRequest): Response<RazorpayOrderResponse>

    suspend fun verifyPayment(@Body request: VerifyPaymentRequest): Response<VerifyPaymentResponse>
}