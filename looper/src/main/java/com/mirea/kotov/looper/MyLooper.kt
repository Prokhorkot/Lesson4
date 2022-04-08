package com.mirea.kotov.looper

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class MyLooper: Thread() {
    private val TAG = "MyLooper"

    private var number: Int = 0
    var handler: Handler? = null

    override fun run() {
        Log.d(TAG, "run")
        Looper.prepare()
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                Log.d("MyLooper", number.toString() + ": " + msg.data.getString("KEY"))
                number++
            }
        }
        Looper.loop()
    }
}