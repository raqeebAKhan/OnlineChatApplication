package com.chatapp.swiftchat.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chatapp.swiftchat.models.UserModel
import com.chatapp.swiftchat.repository.AppRepo

class ProfileViewModel : ViewModel() {

    private var appRepo = AppRepo.StaticFunction.getInstance()

    fun getUser(): LiveData<UserModel> {
        return appRepo.getUser()
    }

    fun updateStatus(status: String) {
        appRepo.updateStatus(status)

    }

    fun updateName(userName: String?) {
        appRepo.updateName(userName!!)
    }

    fun updateImage(imagePath: String) {
        appRepo.updateImage(imagePath)
    }


}