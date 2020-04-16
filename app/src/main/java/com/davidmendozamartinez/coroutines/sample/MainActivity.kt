package com.davidmendozamartinez.coroutines.sample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit.setOnClickListener {
            lifecycleScope.launch {
                val success1 = async(Dispatchers.IO) {
                    validateLogin1(
                        username.text.toString(),
                        password.text.toString()
                    )
                }

                val success2 = async(Dispatchers.IO) {
                    validateLogin2(
                        username.text.toString(),
                        password.text.toString()
                    )
                }

                toast(if (success1.await() && success2.await()) "Success" else "Failure")
            }
        }
    }

    private fun validateLogin1(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateLogin2(username: String, password: String): Boolean {
        Thread.sleep(3000)
        return username.isNotEmpty() && password.isNotEmpty()
    }
}

private fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}