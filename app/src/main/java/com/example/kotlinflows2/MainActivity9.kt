package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity9 : AppCompatActivity() {
    private val TAG = "MainActivity8"

    //MutableSharedFlow) not maintain value
    //MutableStateFlow(Hot) maintain the last value
    //StateFlow(Hot)
//    live data vs stateflow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()
    }

    private fun consumer() {
        GlobalScope.launch(Dispatchers.Main) {

            //mutableSharedFlow
//            val result = producer1()
            /*delay(6000)
            result.collect {
                Log.d(TAG, "consumer: $it")
            }*/
            //MutableStateFlow
//            val result = producer2()
            /*delay(6000)
            result.collect {
                Log.d(TAG, "consumer: $it")
            }*/
            val result = producer3()
            result.collect{
                Log.d(TAG, "consumer: ${result.value}")
            }
        }
    }

    private fun producer1(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>()
        GlobalScope.launch {
            val list = listOf(1, 2, 3, 4, 5, 6)
            list.forEach {
                mutableSharedFlow.emit(it)
                Log.d(TAG, "Emitting-: $it")
                delay(1000)
            }
        }
        return mutableSharedFlow
    }

    private fun producer2(): Flow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }

    private fun producer3(): StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }
}