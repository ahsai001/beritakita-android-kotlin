package com.ahsailabs.beritakita_kotlin.utils

import android.content.Context
import com.ahsailabs.beritakita_kotlin.configs.Config
import com.ahsailabs.beritakita_kotlin.ui.login.models.LoginData

/**
 * Created by ahmad s on 06/05/21.
 */
object SessionUtil {
    fun login(context: Context, loginData: LoginData) {
        val editor = context.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(Config.DATA_TOKEN, loginData.token)
        editor.putString(Config.DATA_NAME, loginData.name)
        editor.putString(Config.DATA_USERNAME, loginData.username)
        editor.putBoolean(Config.DATA_ISLOGGEDIN, true)
        editor.apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(
            Config.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getBoolean(Config.DATA_ISLOGGEDIN, false)
    }

    fun getLoginData(context: Context): LoginData {
        val sharedPreferences = context.getSharedPreferences(
            Config.APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val loginData = LoginData()
        loginData.token = sharedPreferences.getString(Config.DATA_TOKEN, "")
        loginData.username = sharedPreferences.getString(Config.DATA_USERNAME, "")
        loginData.name = sharedPreferences.getString(Config.DATA_NAME, "")
        return loginData
    }

    fun logout(context: Context) {
        val editor = context.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE).edit()
        editor.remove(Config.DATA_TOKEN)
        editor.remove(Config.DATA_NAME)
        editor.remove(Config.DATA_USERNAME)
        editor.remove(Config.DATA_ISLOGGEDIN)
        editor.apply()
    }
}