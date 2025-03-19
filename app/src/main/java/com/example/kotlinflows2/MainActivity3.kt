package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    private val TAG = "MainActivity3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        
        //multiple consume and how to cancel
        consumer1()

        //if second consumer join after 3.5 sec than also it will get the data from starting
        consumer2()

    }

    private fun consumer1() {
         GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d(TAG, "consumer1: ${it}")
            }
        }
    }

    private fun consumer2() {
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            delay(3500)
            data.collect {
                Log.d(TAG, "consumer2: ${it}")
            }
        }
    }

    private fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5, 6)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }

}