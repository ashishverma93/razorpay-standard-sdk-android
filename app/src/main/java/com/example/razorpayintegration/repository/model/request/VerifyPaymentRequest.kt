package com.example.razorpayintegration.repository.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VerifyPaymentRequest(
    @Expose @SerializedName("razorpayPaymentId") val razorpayPaymentId: String,
    @Expose @SerializedName("razorpayOrderId") val razorpayOrderId: String,
    @Expose @SerializedName("razorpaySignature") val razorpaySignature: String,
    @Expose @SerializedName("orderKey") val orderKey: String
) {
    override fun toString(): String {
        return this.toString()
    }
}