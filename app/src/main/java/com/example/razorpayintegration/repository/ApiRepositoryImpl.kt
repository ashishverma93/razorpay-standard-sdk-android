package com.example.razorpayintegration.repository

import com.example.razorpayintegration.network.NetworkModule
import com.example.razorpayintegration.repository.model.request.RazorpayOrderRequest
import com.example.razorpayintegration.repository.model.request.VerifyPaymentRequest
import com.example.razorpayintegration.repository.model.response.RazorpayOrderResponse
import com.example.razorpayintegration.repository.model.response.VerifyPaymentResponse
import retrofit2.Response

object ApiRepositoryImpl : ApiRepository {

    private lateinit var instance: ApiRepository

    fun getInstance(): ApiRepository {
        if (!this::instance.isInitialized) {
            instance = ApiRepositoryImpl
        }
        return instance
    }

    override suspend fun razorpayOrderRequest(request: RazorpayOrderRequest): Response<RazorpayOrderResponse> {
        return NetworkModule.getInstance().razorpayOrderRequest(request)
    }

    override suspend fun verifyPayment(request: VerifyPaymentRequest): Response<VerifyPaymentResponse> {
        return NetworkModule.getInstance().verifyPayment(request)
    }
}