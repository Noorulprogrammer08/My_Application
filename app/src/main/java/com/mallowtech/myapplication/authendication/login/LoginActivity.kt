package com.mallowtech.myapplication.authendication.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.mallowtech.myapplication.databinding.ActivityLoginBinding
import com.mallowtech.myapplication.recyclerview.JobListScreen

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        with(binding) {
            with(loginViewModel) {
                etUsername.doAfterTextChanged {
                    username = it.toString()
                }
                etPassword.doAfterTextChanged {
                    password = it.toString()
                }
                btnLogin.setOnClickListener {
                    doLogin()
                }
            }
            tvSignup.setOnClickListener {
                startActivity(Intent(this@LoginActivity, JobListScreen::class.java))
                finish()
            }
        }
    }

    private fun observeViewModel() {
        with(binding) {
            with(loginViewModel) {
                validationErrorUsername.observe(this@LoginActivity) {
                    etUsername.error = getString(it)
                }
                validationErrorPassword.observe(this@LoginActivity) {
                    etPassword.error = getString(it)
                }
                onLoginSuccess.observe(this@LoginActivity) { passIntentOk ->
                    if (passIntentOk) {
                        val intent = Intent(this@LoginActivity, JobListScreen::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                loginErrorResponse.observe(this@LoginActivity) { error ->
                    AlertDialog.Builder(this@LoginActivity).setTitle("Error:").setMessage(error)
                        .create().show()
                }
            }
        }
    }

}