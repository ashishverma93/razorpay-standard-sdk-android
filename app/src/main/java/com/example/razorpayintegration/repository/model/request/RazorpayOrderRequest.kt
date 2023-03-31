package com.example.razorpayintegration.repository.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RazorpayOrderRequest(
    @Expose @SerializedName("customerName") val customerName: String,
    @Expose @SerializedName("customerEmail") val customerEmail: String,
    @Expose @SerializedName("customerMobile") val customerMobile: String,
    @Expose @SerializedName("amount") val amount: Long
) {
    override fun toString(): String {
        return this.toString()
    }
}