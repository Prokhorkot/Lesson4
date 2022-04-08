package com.mirea.kotov.lesson4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var infoTextView: TextView? = null
    private var button: Button? = null

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoTextView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        //region Thread renaming

        val mainThread: Thread = Thread.currentThread()
        infoTextView?.text = "Текущий поток: " + mainThread.name

        mainThread.name = "MireaThread"

        infoTextView?.append("\nНовое имя потока: " + mainThread.name)

        //endregion

        //region Click handlers setup

        button?.setOnClickListener{
            val runnable: Runnable = object: Runnable{
                override fun run() {
                    val numberThread = counter++
                    Log.i("ThreadProject", "Запущен поток №$numberThread")
                    val endTime: Long = System.currentTimeMillis() + 20 * 1000

                    while(System.currentTimeMillis() < endTime){
                        synchronized(this){
                            try{
                                TimeUnit.MILLISECONDS.sleep(endTime - System.currentTimeMillis())
                            } catch (e: Exception){}
                        }
                    }

                    Log.i("ThreadProject", "Выполнен поток № $numberThread")
                }
            }

            val thread = Thread(runnable)
            thread.start()
        }

        //endregion
    }
}