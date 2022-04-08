package com.mirea.kotov.looper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private var myLooper: MyLooper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

        button?.setOnClickListener{onButtonClick()}

        myLooper = MyLooper()
        myLooper?.start()
    }

    private fun onButtonClick(){
        var message = Message()
        var bundle = Bundle()

        bundle.putString("KEY", "19 лет, программист")
        message.data = bundle

        myLooper?.handler?.sendMessage(message)
    }
}