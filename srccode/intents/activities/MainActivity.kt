package com.example.eothein.intentexample.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.example.eothein.intentexample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    companion object {

        /**
         * Generates an intent to create a [FullscreenActivity]
         */
        fun newFullScreenIntent(context: Context): Intent {
            val intent = Intent(context, FullscreenActivity::class.java)
            return intent
        }
    }

}
