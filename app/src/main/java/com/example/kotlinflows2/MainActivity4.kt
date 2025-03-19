package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity4 : AppCompatActivity() {
    private val TAG = "MainActivity4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()
    }

    private fun consumer() {
        GlobalScope.launch {
            val data: Flow<Int> = producer()
                .onStart {
                    emit(-1)
                    Log.d(TAG, "consumer: onStart")
                }.onCompletion {
                    emit(-6)
                    Log.d(TAG, "consumer: onCompletion ")

                }.onEach {
                    Log.d(TAG, "consumer1: onEach $it")

                }
            data.collect {
                Log.d(TAG, "consumer1: ${it}")
            }
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