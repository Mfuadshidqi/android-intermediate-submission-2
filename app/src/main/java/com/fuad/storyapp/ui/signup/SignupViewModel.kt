package com.fuad.storyapp.ui.signup

import androidx.lifecycle.ViewModel
import com.fuad.storyapp.data.repository.UserRepository

class SignupViewModel(private val repo: UserRepository) : ViewModel() {

    fun register(name: String, email: String, password: String) = repo.register(name, email, password)
}