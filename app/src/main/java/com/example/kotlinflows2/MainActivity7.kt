package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity7 : AppCompatActivity() {
    private val TAG = "MainActivity7"

    //when producer fast and consumer slow then use buffer(int)
    //        .buffer-->
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()

    }

    private fun consumer() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                producer()
                    .buffer(3)
                    .collect {
                    delay(1500)
                    Log.d(TAG, "consumer1: ${it}")
                }
            }
            Log.d(TAG, "consumer: $time")
        }
    }

    private fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}