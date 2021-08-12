package com.chatapp.swiftchat.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.chatapp.swiftchat.R
import com.chatapp.swiftchat.viewmodels.LoginViewModel
import com.chatapp.swiftchat.viewmodelfactory.LoginViewModelFactory
import com.swiftchatapp.swiftchat.activities.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = LoginViewModelFactory(this).create(LoginViewModel::class.java)

        tvSignUpText.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        btnLoginBtn.setOnClickListener {

            if (editFieldValidation()) {
                loginViewModel.fetchDataFromDatabase(
                    etvLoginEmail.text.toString(), etvLoginPassword.text.toString()
                ).observe(this, Observer {
                    if (it != null) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("userName", it.fullName)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }
    }

    private fun editFieldValidation(): Boolean {

        if (etvLoginEmail.text.toString().isEmpty()) {
            etvLoginEmail.error = "Email cannot be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etvLoginEmail.text).matches()) {
            etvLoginEmail.error = getString(R.string.invalid_email)
            return false
        }

        if (etvLoginPassword.text.toString().isEmpty()) {
            etvLoginPassword.error = "Password cannot be empty"
            return false
        } else if (!isPasswordValid(etvLoginPassword.text.toString())) {
            etvLoginPassword.error = getString(R.string.invalid_password)
            return false
        } else if (!passwordPattern.matcher(etvLoginPassword.text).matches()) {
            etvLoginPassword.error = getString(R.string.weak_password)
            return false
        }
        return true
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 8;
    }

    private val passwordPattern: Pattern =
        Pattern.compile(
            "^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$"
        )

    private fun forgotEmail(): Boolean {

        return true
    }
}