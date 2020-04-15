package com.davidmendozamartinez.coroutines.sample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit.setOnClickListener {
            val success = validateLogin(username.text.toString(), password.text.toString())
            toast(if (success) "Success" else "Failure")
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }
}

private fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}