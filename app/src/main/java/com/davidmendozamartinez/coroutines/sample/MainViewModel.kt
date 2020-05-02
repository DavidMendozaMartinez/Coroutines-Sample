package com.davidmendozamartinez.coroutines.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    fun onSubmitClicked(username: String, password: String) = liveData(ioDispatcher) {
        emit(validateLogin(username, password))
    }

    private fun validateLogin(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }
}