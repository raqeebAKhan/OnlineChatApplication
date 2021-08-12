package com.chatapp.swiftchat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.chatapp.swiftchat.R
import com.chatapp.swiftchat.util.AppUtil
import com.chatapp.swiftchat.fragments.ChatFragment
import com.chatapp.swiftchat.viewmodelfactory.UserViewModelFactory
import com.chatapp.swiftchat.viewmodels.UserViewModel
import com.swiftchatapp.swiftchat.fragments.ContactFragment
import com.swiftchatapp.swiftchat.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragment: Fragment? = null
    private var userViewModel: UserViewModel? = null
    private lateinit var appUtil: AppUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appUtil= AppUtil()

        userViewModel = UserViewModelFactory(this).create(UserViewModel::class.java)
        fetchDataBase()


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainScreenContainer, ChatFragment()).commit()
            bottomChip.setItemSelected(R.id.btnChat)
        }
        bottomChip.setOnItemSelectedListener { id ->
            when (id) {
                R.id.btnChat -> {
                   fragment = ChatFragment()
                }

                R.id.btnProfile -> {
                 fragment = ProfileFragment()

                }

                R.id.btnContact -> fragment = ContactFragment()
            }

            fragment!!.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainScreenContainer, fragment!!)
                    .commit()
            }
        }
    }

    private fun fetchDataBase() {
        val userName = intent.getStringExtra("userName")
    }

    override fun onPause() {
        super.onPause()
        appUtil.updateOnlineStatus("offline")
    }

    override fun onResume() {
        super.onResume()
        appUtil.updateOnlineStatus("online")
    }
}