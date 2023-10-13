package com.khaleejtimes.test.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.khaleejtimes.test.MainActivity
import com.khaleejtimes.test.databinding.ActivityLoginBinding
import com.khaleejtimes.test.utils.Constants.EMAIL_ADDRESS_PATTERN
import com.khaleejtimes.test.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    private val TAG = "SignUpActivity"
    //


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.txtSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))

        }

        binding.btnContinue.setOnClickListener {
            if (binding.etEmail.text.isEmpty()) {
                showToast("Please Enter Email")
            } else if (!isValidString(binding.etEmail.text.toString())){
                showToast("Email is not valid")
            }
            else if (binding.etPassword.text.isEmpty()) {
                showToast("Please Enter Password")
            }
            else if (binding.etPassword.text.length < 6) {
                showToast("Password Characters should not be less than 6")
            }
            else {
                auth.signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            startActivity(Intent(this, MainActivity::class.java))

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
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
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }
}
