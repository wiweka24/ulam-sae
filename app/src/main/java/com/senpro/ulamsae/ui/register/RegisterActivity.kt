package com.senpro.ulamsae.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.senpro.ulamsae.R
import com.senpro.ulamsae.databinding.ActivityRegisterBinding
import com.senpro.ulamsae.model.request.LoginRequest
import com.senpro.ulamsae.model.request.RegisterRequest
import com.senpro.ulamsae.ui.MainActivity
import com.senpro.ulamsae.ui.ViewModelFactory
import com.senpro.ulamsae.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[RegisterActivityViewModel::class.java]
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            val username = binding.emailEditText.text.toString()
            val fullname = binding.fullnameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                username.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                fullname.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan nama lengkap"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Masukkan password"
                }
                else -> {
                    val newUser = RegisterRequest(username, fullname, password)
                    val registerLiveData = viewModel.register(newUser)
                    registerLiveData.observe(this) { success ->
                        if (success) {
//                            showLoading(false)
                            val intent = Intent(this, LoginActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        } else {
//                            showLoading(false)
                            val toast: Toast = Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT)
                            toast.show()
                        }
                    }
                }
            }
        }

        binding.tvGoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}