package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity10 : AppCompatActivity() {
    private val TAG = "MainActivity8"

    //SharedFlow(HOT)
//        .replay(Int)-->last value stored
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer1()
        consumer2()
    }

    private fun consumer1() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect{
                Log.d(TAG, "consumer1: ${it}")
            }
        }
    }
    private fun consumer2() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect{
                delay(3000)
                Log.d(TAG, "consumer2: ${it}")
            }
        }
    }

    private fun producer(): Flow<Int> {
//        val mutableSharedFlow = MutableSharedFlow<Int>()
        val mutableSharedFlow = MutableSharedFlow<Int>(replay = 3)
        GlobalScope.launch {
            val list = listOf(1, 2, 3, 4, 5, 6)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }
}