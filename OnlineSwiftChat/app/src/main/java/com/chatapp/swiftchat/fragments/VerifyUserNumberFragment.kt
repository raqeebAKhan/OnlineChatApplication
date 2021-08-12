package com.chatapp.swiftchat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chatapp.swiftchat.R
import com.chatapp.swiftchat.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_verify_user_number.view.*

class VerifyUserNumberFragment : Fragment() {

    private var code: String? = null
    private lateinit var pin: String
    private var firebaseAuth: FirebaseAuth? = null
    private var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            code = it.getString("Code")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_user_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        view.btnVerify.setOnClickListener {
            if (checkPin()) {
                val credential = PhoneAuthProvider.getCredential(code!!, pin)
                signInUser(credential)
            }
        }
    }

    private fun signInUser(credential: PhoneAuthCredential) {
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val userModel =
                    UserModel(
                        "", "", "",
                        firebaseAuth!!.currentUser!!.phoneNumber!!,
                        firebaseAuth!!.uid!!
                    )

                databaseReference!!.child(firebaseAuth?.uid!!).setValue(userModel)
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.otp_container, GetUserDataFragment())
                    .commit()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(code: String) =
            VerifyUserNumberFragment().apply {
                arguments = Bundle().apply {
                    putString("Code", code)
                }
            }
    }

    private fun checkPin(): Boolean {
        pin = requireView().otp_text_view.text.toString()
        if (pin.isEmpty()) {
            requireView().otp_text_view.error = "Filed is required"
            return false
        } else if (pin.length < 6) {
            requireView().otp_text_view.error = "Enter valid pin"
            return false
        } else return true
    }

}