package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainActivity5 : AppCompatActivity() {
    private val TAG = "MainActivity5"

    //    operators-->terminal and non terminal
//    Terminal operator--> collect()(to start the flow)


    //first,list,map,filter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()

    }

    private fun consumer() {
        GlobalScope.launch {
            var first = producer().first()
            var list = producer().toList()
            Log.d(TAG, "consumer first: ${first}")
            Log.d(TAG, "consumer toList: ${list}")

            producer().map {
                    it * 2
                }.filter {
                    it < 3
                }.collect {
                    Log.d(TAG, "consumer map & filter: $it")
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