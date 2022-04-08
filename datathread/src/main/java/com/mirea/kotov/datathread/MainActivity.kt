package com.mirea.kotov.datathread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var typeInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        typeInfo = findViewById(R.id.typeInfo)

        val runn1: Runnable = Runnable {
            typeInfo?.text = "runn1"
        }

        val runn2: Runnable = Runnable {
            typeInfo?.text = "runn2"
        }

        val runn3: Runnable = Runnable {
            typeInfo?.text = "runn3"
        }

        val thread = Thread(Runnable {
            try{
                TimeUnit.SECONDS.sleep(2)
                runOnUiThread(runn1)
                TimeUnit.SECONDS.sleep(1)
                typeInfo?.postDelayed(runn3, 2000)
                typeInfo?.post(runn2)
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        })
        thread.start()
    }
}