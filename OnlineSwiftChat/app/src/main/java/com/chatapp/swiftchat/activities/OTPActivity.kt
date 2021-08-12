package com.chatapp.swiftchat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chatapp.swiftchat.R
import com.chatapp.swiftchat.fragments.GetUserNumberFragment

class OTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
        supportFragmentManager.beginTransaction()
            .add(R.id.otp_container, GetUserNumberFragment())
            .commit()
    }
}