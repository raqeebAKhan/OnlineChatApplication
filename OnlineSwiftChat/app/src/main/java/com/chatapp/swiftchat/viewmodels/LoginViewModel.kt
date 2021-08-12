package com.chatapp.swiftchat.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chatapp.swiftchat.database.LoginData
import com.chatapp.swiftchat.database.UserDatabase

class LoginViewModel(private val context: Context) : ViewModel() {
    /* Method to fetch the stored data from room database*/

    fun fetchDataFromDatabase(email: String, password: String): LiveData<LoginData> {
        return UserDatabase.getInstance(context).userDao.getUserDetails(email, password)
    }
}