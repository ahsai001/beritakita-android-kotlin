package com.ahsailabs.beritakita_kotlin.configs

/**
 * Created by ahmad s on 01/09/20.
 */
object Config {
    private const val BASE_URL = "https://api.zaitunlabs.com/kango/cijou/"
    const val API_KEY = "qwerty123456"
    const val GROUP_CODE = "ABJAL1"

    const val loginUrl: String = BASE_URL + "login"
    val newsListUrl: String
        get() = BASE_URL + "news/all"
    val newsDetailUrl: String
        get() = BASE_URL + "news/detail/{id}"
    val addNewsUrl: String
        get() = BASE_URL + "news/add"

    const val APP_PREFERENCES = "beritakita_preferences"
    const val DATA_TOKEN = "data_token"
    const val DATA_NAME = "data_name"
    const val DATA_USERNAME = "data_username"
    const val DATA_ISLOGGEDIN = "data_isloggedin"
}