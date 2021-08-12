package com.chatapp.swiftchat.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.chatapp.swiftchat.database.LoginData
import com.chatapp.swiftchat.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val context: Context) : ViewModel() {

    /* Method to insert the data into room database*/

    fun insertDataToDatabase(
        firstName: String,
        mobileNumber: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val user =
                LoginData(
                    fullName = firstName, mobileNumber
                    = mobileNumber, email = email, password = password, confirmPassword = confirmPassword
                )
            UserDatabase.getInstance(context).userDao.insertUser(user)
        }
    }
}