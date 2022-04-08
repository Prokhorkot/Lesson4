package com.mirea.kotov.loadermanager

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText
import androidx.loader.content.AsyncTaskLoader
import kotlin.random.Random


class MyLoader(context: Context) : AsyncTaskLoader<String>(context){
    private var text: String? = null

    constructor(context: Context, args: Bundle?) : this(context) {
        if (args != null){
            text = args.getString("text")
            var textArray: CharArray = text!!.toCharArray()

            for (i in textArray.size - 1 downTo 1) {
                val j = Random.nextInt(i + 1)
                val copy = textArray[i]
                textArray[i] = textArray[j]
                textArray[j] = copy
            }
            text = String(textArray)
        }

    }

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
        return text
    }



}