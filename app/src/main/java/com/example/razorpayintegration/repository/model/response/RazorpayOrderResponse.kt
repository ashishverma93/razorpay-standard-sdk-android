package com.example.razorpayintegration.repository.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RazorpayOrderResponse(
    @Expose @SerializedName("orderKey") val orderKey: String,
    @Expose @SerializedName("order") val order: String
) {
    override fun toString(): String {
        return this.toString()
    }
}

data class OrderResponse(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("entity") @Expose val entity: String,
    @SerializedName("amount") @Expose val amount: Int,
    @SerializedName("amount_paid") @Expose val amountPaid: Int,
    @SerializedName("amount_due") @Expose val amountDue: Int,
    @SerializedName("currency") @Expose val currency: String,
    @SerializedName("receipt") @Expose val receipt: String,
    @SerializedName("status") @Expose val status: String,
    @SerializedName("attempts") @Expose val attempts: Int,
    @SerializedName("notes") @Expose val notes: Map<String, Any>?,
    @SerializedName("created_at") @Expose val createdAt: Int
) {
    override fun toString(): String {
        return this.toString()
    }
}