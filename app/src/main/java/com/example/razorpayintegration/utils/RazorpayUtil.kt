package com.example.razorpayintegration.utils

import com.razorpay.PayloadHelper
import org.json.JSONObject

object RazorpayUtil {
    fun checkoutObj(orderId: String): PayloadHelper {
        val payloadHelper = PayloadHelper("INR", 10000, orderId)
        payloadHelper.name = "Customer Name" // Ashish Verma
        payloadHelper.description = "Description"
        payloadHelper.prefillEmail = "user@domain.com" // Customer Email
        payloadHelper.prefillContact = "9876543210" // Customer Mobile
        payloadHelper.prefillName = "Merchant Name" // XYZ Private Limited
        payloadHelper.sendSmsHash = false
        payloadHelper.retryMaxCount = 4
        payloadHelper.retryEnabled = true
        payloadHelper.color = "#3399CC"
        payloadHelper.backDropColor = "#ffffff"
        payloadHelper.hideTopBar = true
        payloadHelper.notes = JSONObject("{\"remarks\":\"Payment for purchasing\"}")
        payloadHelper.readOnlyEmail = true
        payloadHelper.readOnlyContact = true
        payloadHelper.readOnlyName = true
        payloadHelper.image = "https://www.razorpay.com"
        return payloadHelper
    }
}