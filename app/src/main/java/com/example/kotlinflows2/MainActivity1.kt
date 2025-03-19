package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity1 : AppCompatActivity() {

    private val TAG = "MainActivity"
    //channel rarely used
    val channel = Channel<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        producer()
        consumer()
    }

    private fun producer() {
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }
    }

    private fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "consumer: $channel.receive()")
            Log.d(TAG, "consumer: $channel.receive()")

        }
    }
}