package org.yuno.apps.lurk.screens.main

import android.content.Context
import androidx.preference.PreferenceManager

class Database(context:Context) {
    private var preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getConfig(): String = preferences.getString("config", "")!!

    fun setConfig(config:String) {
        val editor = preferences.edit()
        editor.putString("config", config)
        editor.apply()
    }
}