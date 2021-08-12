package com.chatapp.swiftchat.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginData")
data class LoginData(
    @PrimaryKey(autoGenerate = true)
    var loginId: Int = 0,

    @ColumnInfo(name = "fullName")
    var fullName: String,

    @ColumnInfo(name = "phone")
    var mobileNumber: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "confirmPassword")
    var confirmPassword: String
)