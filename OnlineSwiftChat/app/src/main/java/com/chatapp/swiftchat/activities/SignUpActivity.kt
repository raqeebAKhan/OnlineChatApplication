package com.swiftchatapp.swiftchat.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.chatapp.swiftchat.activities.LoginActivity
import com.swiftchatapp.swiftchat.R
import com.chatapp.swiftchat.viewmodels.UserViewModel
import com.chatapp.swiftchat.viewmodelfactory.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userViewModel = UserViewModelFactory(this).create(UserViewModel::class.java)

        tvLoginText.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        btnRegister.setOnClickListener {
                if (editFieldValidation()) {
                    userViewModel.insertDataToDatabase(
                        firstName = etvFullName.text.toString(),
                        mobileNumber = etvPhone.text.toString(),
                        email = etvSignUpEmail.text.toString(),
                        password = etvSignUpPassword.text.toString(),
                        confirmPassword = etvSignUpConfirmPassword.text.toString()
                    )

                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    intent.putExtra(etvSignUpEmail.text.toString(), "userEmail")
                    intent.putExtra(etvSignUpPassword.text.toString(), "userPassword")
                    intent.putExtra(etvSignUpConfirmPassword.text.toString(), "userConfirmPassword")
                    startActivity(intent)
                    finish()
                }
        }
    }

    private fun editFieldValidation(): Boolean {
        if (etvFullName.text.toString().isEmpty()) {
            etvFullName.error = "Full Name cannot be empty"
            return false
        }

        if (etvPhone.text.toString().isEmpty()) {
            etvPhone.error = "Phone Number cannot be empty"
            return false
        } else if (!isMobileValid(etvPhone.text.toString())) {
            etvPhone.error = getString(R.string.invalid_mobile_number)
            return false
        } else if (!Patterns.PHONE.matcher(etvPhone.text).matches()) {
            etvPhone.error = getString(R.string.weak_mobile_number)
            return false
        }

        if (etvSignUpEmail.text.toString().isEmpty()) {
            etvSignUpEmail.error = "Email cannot be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etvSignUpEmail.text).matches()) {
            etvSignUpEmail.error = getString(R.string.invalid_email)
            return false
        }

        if (etvSignUpPassword.text.toString().isEmpty()) {
            etvSignUpPassword.error = "Password cannot be empty"
            return false
        } else if (!isPasswordValid(etvSignUpPassword.text.toString())) {
            etvSignUpPassword.error = getString(R.string.invalid_password)
            return false
        } else if (!passwordPattern.matcher(etvSignUpPassword.text).matches()) {
            etvSignUpPassword.error = getString(R.string.weak_password)
            return false
        }

        if (!etvSignUpPassword.text.toString().equals(etvSignUpConfirmPassword.text.toString())) {
            etvSignUpConfirmPassword.error = getString(R.string.invalid_confirm_password)
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
                    ".{8,}" +               //at least 8 characters
                    "$"
        )

    private fun isMobileValid(mobile: String): Boolean {
        return mobile.length == 10
    }

}