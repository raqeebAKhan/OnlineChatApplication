package com.chatapp.swiftchat.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chatapp.swiftchat.database.LoginData

@Dao
interface UserDao {
    /**
     * Insert operation to insert the data
     */

    @Insert
    suspend fun insertUser(loginData: LoginData)

    /**
     * Query operation to fetch the stored data
     */

    @Query("Select * FROM LoginData Where email = :email and password =:password")
    fun getUserDetails(email: String, password : String): LiveData<LoginData>

}