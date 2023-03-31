package com.example.razorpayintegration.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.razorpayintegration.repository.ApiRepositoryImpl
import com.example.razorpayintegration.repository.model.request.RazorpayOrderRequest
import com.example.razorpayintegration.repository.model.request.VerifyPaymentRequest
import com.example.razorpayintegration.repository.model.response.RazorpayOrderResponse
import com.example.razorpayintegration.repository.model.response.VerifyPaymentResponse
import com.razorpay.PaymentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MainViewModel : ViewModel() {

    private lateinit var viewModel: MainViewModel

    fun getInstance(): MainViewModel {
        if (!this::viewModel.isInitialized) {
            viewModel = MainViewModel
        }
        return viewModel
    }

    private val razorpayOrderLiveData = MutableLiveData<RazorpayOrderResponse>()
    fun observeRazorpayOrderLiveData() = razorpayOrderLiveData

    private val progressLiveData = MutableLiveData<Boolean>()
    fun observeProgressLiveData() = progressLiveData

    private val errorResponseLiveData = MutableLiveData<String>()
    fun observeErrorResponseLiveData() = errorResponseLiveData

    private val verifyPaymentLiveData = MutableLiveData<VerifyPaymentResponse>()
    fun observeVerifyPaymentLiveData() = verifyPaymentLiveData

    fun razorpayOrderRequest() {
        progressLiveData.postValue(true)
        val request = RazorpayOrderRequest(
            "User Name", "user@domain.com",
            "9876543210", 10000
        )
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiRepositoryImpl.getInstance().razorpayOrderRequest(request)
            if (response.isSuccessful && response.code() == 200) {
                val body = response.body()
                body?.let { b ->
                    razorpayOrderLiveData.postValue(b)
                } ?: run {
                    errorResponseLiveData.postValue("Something went wrong")
                }
                progressLiveData.postValue(false)
            } else {
                val error = response.errorBody()
                error?.let { e ->
                    errorResponseLiveData.postValue(e.string())
                }
                progressLiveData.postValue(false)
            }
        }
    }

    fun verifyPayment(paymentData: PaymentData, orderKey: String) {
        progressLiveData.postValue(true)
        val request = VerifyPaymentRequest(
            paymentData.paymentId, paymentData.orderId,
            paymentData.signature, orderKey
        )
        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiRepositoryImpl.getInstance().verifyPayment(request)
            if (response.isSuccessful && response.code() == 200) {
                val body = response.body()
                body?.let { b ->
                    verifyPaymentLiveData.postValue(b)
                } ?: run {
                    errorResponseLiveData.postValue("Something went wrong")
                }
                progressLiveData.postValue(false)
            } else {
                val error = response.errorBody()
                error?.let { e ->
                    errorResponseLiveData.postValue(e.string())
                }
                progressLiveData.postValue(false)
            }
        }
    }

}