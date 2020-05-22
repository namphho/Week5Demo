package com.hnam.week5demo

import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.os.storage.StorageManager.ACTION_MANAGE_STORAGE
import android.util.Log
import java.io.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private val PREFERENCE_NAME = "SETTING_FILES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preference = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val prefEditor = preference.edit()
        prefEditor.putBoolean("KEY-ACCOUNT", true)
        prefEditor.putString("KEY-EMAIL", "nam.phho@gmail.com")
        prefEditor.apply()

    }
}
