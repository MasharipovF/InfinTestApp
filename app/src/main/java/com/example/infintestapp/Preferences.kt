package com.example.infintestapp

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"


    fun init(context: Context) {
        preferences = context.getSharedPreferences(ACCESS_TOKEN, Context.MODE_PRIVATE)
    }

    fun setPreference(preferences: SharedPreferences) {
        Preferences.preferences = preferences
    }

    lateinit var preferences: SharedPreferences

    var token: String?
        get() = preferences.getString(Preferences::token.name, null)
        set(value) {
            preferences.edit().putString(Preferences::token.name, value).apply()
        }


}