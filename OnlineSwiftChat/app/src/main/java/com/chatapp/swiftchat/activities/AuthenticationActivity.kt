package com.chatapp.swiftchat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chatapp.swiftchat.R
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        btnSignup.setOnClickListener {
            val signUpIntent = Intent(this@AuthenticationActivity, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        btnLogin.setOnClickListener {
            val loginIntent = Intent(this@AuthenticationActivity, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        btnOTP.setOnClickListener {
            val otpIntent = Intent(this@AuthenticationActivity, OTPActivity::class.java)
            startActivity(otpIntent)
        }
    }
}