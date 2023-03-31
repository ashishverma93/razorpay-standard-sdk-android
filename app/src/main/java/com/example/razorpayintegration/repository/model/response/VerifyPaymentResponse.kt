package com.example.razorpayintegration.repository.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VerifyPaymentResponse(
    @Expose @SerializedName("flag") val flag: Boolean
) {
    override fun toString(): String {
        return this.toString()
    }
}