package com.khaleejtimes.test.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.khaleejtimes.test.MainActivity
import com.khaleejtimes.test.databinding.ActivitySignUpBinding
import com.khaleejtimes.test.utils.Constants
import com.khaleejtimes.test.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private val TAG = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     binding =  ActivitySignUpBinding.inflate(layoutInflater)
         setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                showToast("Enter Name")
            }
           else if (binding.etEmail.text.isEmpty()) {
                showToast("Please Enter Email")
            } else if (!isValidString(binding.etEmail.text.toString())){
                showToast("Email is not valid")
            }
            else if (binding.etPassword.text.isEmpty()) {
                showToast("Please Enter Password")
            }
            else if (binding.etPassword.text.length < 6) {
                showToast("Password Characters should not be less than 6")
            } else {


                auth.createUserWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                           startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }

            }
        }

    }

    private fun isValidString(str: String): Boolean{
        return Constants.EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }
}