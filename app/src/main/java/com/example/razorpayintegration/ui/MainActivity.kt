package com.example.razorpayintegration.ui

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.razorpayintegration.R
import com.example.razorpayintegration.repository.model.response.OrderResponse
import com.example.razorpayintegration.utils.RazorpayUtil
import com.google.gson.Gson
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
// Step 3 PaymentResultWithDataListener
class MainActivity : AppCompatActivity(), PaymentResultWithDataListener  {

    private lateinit var mProgressDialog: ProgressDialog

    private var orderKey: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Step 2
        Checkout.preload(applicationContext)
        Checkout.sdkCheckIntegration(this)

        observeRazorpayOrderLiveData()
        observeProgressLiveData()
        observeErrorResponseLiveData()
        observeVerifyPaymentLiveData()

        mProgressDialog = ProgressDialog(this)

        findViewById<AppCompatButton>(R.id.btnPayNow).setOnClickListener {
            // Step 4
            MainViewModel.getInstance().razorpayOrderRequest()
        }
    }

    private fun observeRazorpayOrderLiveData() {
        MainViewModel.getInstance().observeRazorpayOrderLiveData().observe(this) {
            it?.let { data ->
                // This order key is generated by your server not razorpay server
                orderKey = data.orderKey
                val orderResponse: OrderResponse =
                    Gson().fromJson(data.order, OrderResponse::class.java)
                // Step 5
                startPayment(orderResponse.id)
            }
        }
    }

    private fun startPayment(orderId: String) {
        val co = Checkout()
        // provide your razorpay key id for test or live environment
        // keyId will be available in Account & Settings
        // under API Keys tab of Razorpay Dashboard
        // Below is the sample test Key ID
        // You can set this key in gradle file and
        // can use from BuildConfig for each build variants
        co.setKeyID("rzp_test_Efdlaldsk")
        val payLoad = RazorpayUtil.checkoutObj(orderId)
        val activity: Activity = this
        co.open(activity, payLoad.getJson())
    }

    private fun observeProgressLiveData() {
        MainViewModel.getInstance().observeProgressLiveData().observe(this) {
            it?.let { flag ->
                if (flag) {
                    mProgressDialog.setMessage("Order creation in progress...")
                    mProgressDialog.show()
                } else {
                    mProgressDialog.dismiss()
                }
            }
        }
    }

    private fun observeErrorResponseLiveData() {
        MainViewModel.getInstance().observeErrorResponseLiveData().observe(this) {
            it?.let { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeVerifyPaymentLiveData() {
        MainViewModel.getInstance().observeVerifyPaymentLiveData().observe(this) {
            it?.let { data ->
                if (data.flag) {
                    Toast.makeText(this, "Payment Verify Successful", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // This method will get call once the payment was successfully done on razorpay sdk
    override fun onPaymentSuccess(razorpayPaymentID: String, paymentData: PaymentData) {
        // Step 6
        MainViewModel.getInstance().verifyPayment(paymentData, orderKey)
    }

    // This method will get call when payment was cancelled or some error occurred
    override fun onPaymentError(code: Int, p1: String?, paymentData: PaymentData) {
        println("code: $code, p1: $p1, PaymentData: $paymentData")
    }
}