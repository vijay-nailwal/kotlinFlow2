package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainActivity8 : AppCompatActivity() {
    private val TAG = "MainActivity8"
    //operators-->flowOn,.catch
//flowOn-->above are running in background and below this flowOn() foreground
//.catch{"not propagate the error in collect, it will only handle in emitter"}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()
    }

    private fun consumer() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                producer()
                    //above are running in background and below this flowOn() foreground
                    .flowOn(Dispatchers.IO).collect {
                        Log.d(TAG, "consumer1: ${Thread.currentThread().name}")
                    }
            } catch (e: Exception) {
                Log.d(TAG, "consumer: ${e.message.toString()}")
            }
        }
    }

    private fun producer() = flow<Int> {
        val list = listOf(1, 2, 3)
        list.forEach {
            delay(1000)
            Log.d(TAG, "producer: ${Thread.currentThread().name}")
            emit(it)
            //1st way->it will propagate the exception to consunmer
            throw Exception("Error in emitter")
        }
    }.catch {
        Log.d(TAG, "producer: emitter catch ${it.message}")
        emit(-1)
    }
}