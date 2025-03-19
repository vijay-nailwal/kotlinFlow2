package com.example.kotlinflows2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflows2.model.FormattedNote
import com.example.kotlinflows2.model.getNotes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity6 : AppCompatActivity() {
    private val TAG = "MainActivity6"

//        .asFlow--> convert list to flow

    //map,filter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        consumer()

    }

    private fun consumer() {
        GlobalScope.launch {
            getNotes().map {
                FormattedNote(it.isActive, it.title.uppercase(), it.description)
            }.filter {
                    it.isActive
                }.collect {
                    Log.d(TAG, "consumer: $it")
                }
        }
    }
}