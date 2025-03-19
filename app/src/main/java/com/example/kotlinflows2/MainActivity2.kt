package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private val TAG = "MainActivity2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //single consume and how to cancel
        consumer()

    }

    private fun consumer(){
        val job = GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d(TAG, "onCreate: ${it}")
            }
        }
        GlobalScope.launch {
            delay(3500)
            //if consumer is cancelled producer will automatically cancel
            job.cancel()
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