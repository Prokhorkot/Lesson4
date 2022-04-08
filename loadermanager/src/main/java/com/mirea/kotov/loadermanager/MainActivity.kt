package com.mirea.kotov.loadermanager

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader


class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    private var button: Button? = null
    private var editText: EditText? = null
    private var textView: TextView? = null

    val TAG = this.javaClass.simpleName
    private val LoaderID = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region Initializing views

        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        //endregion

        //region creating handlers

        button?.setOnClickListener{onButtonClick()}

        //endregion

    }

    override fun onLoaderReset(loader: Loader<String?>) {
        Log.d(TAG, "onLoaderReset")
    }

    override fun onCreateLoader(i: Int, @Nullable bundle: Bundle?): Loader<String> {
        if (i == LoaderID) {
            return MyLoader(this, bundle)
        }
        return MyLoader(this)
    }

    override fun onLoadFinished(loader: Loader<String?>, s: String) {
        if (loader.id == LoaderID) {
            Log.d(TAG, "onLoadFinished$s")
            textView?.text = s
        }
    }

    fun onButtonClick(){
        val bundle = Bundle()
        bundle.putString("text", editText?.text.toString())
        supportLoaderManager.initLoader(LoaderID, bundle, this)
    }
}