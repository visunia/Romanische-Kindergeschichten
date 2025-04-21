package com.kindergeschichten.romanisch.tools

import android.content.Context
import com.kindergeschichten.romanisch.ui.custom.Theme
import com.kindergeschichten.romanisch.ui.languageselection.DEFAULT_LANGUAGE

const val SHARED_PRIVATE = "SHARED_PRIVATE"

const val SELECTED_LANGUAGE = "SELECTED_LANGUAGE"

const val FIRST_OPEN = "FIRST_OPEN"
const val THEME_MODE = "THEME_MODE"
class PreferenceManager private constructor(val context: Context) {

    companion object : SingletonHolder<PreferenceManager, Context>(::PreferenceManager)

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PRIVATE, Context.MODE_PRIVATE)

    var selectedLanguage: String
        get() {
            return sharedPreferences.getString(SELECTED_LANGUAGE, DEFAULT_LANGUAGE)!!
        }
        set(value) {
            sharedPreferences.edit()
                .putString(SELECTED_LANGUAGE, value)
                .commit()
        }

    var firstOpen: Boolean
        get() {
            return sharedPreferences.getBoolean(FIRST_OPEN, true)
        }
        set(value) {
            sharedPreferences.edit().putBoolean(FIRST_OPEN, value).commit()
        }


    var themeMode: Theme
        get() {
            return Theme.fromInt(sharedPreferences.getInt(THEME_MODE, 2))
        }
        set(value) {
            sharedPreferences.edit()
                .putInt(THEME_MODE,value.value)
                .commit()
        }
}