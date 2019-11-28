package app.cntandroidver2.workshop.utils

import android.content.Context

class SharedPrefUtil(context: Context) {

    private val PREF_NAME = "DATAS"
    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        const val KEY_NAME = "NAME"
    }

    var name: String?
        get() = pref.getString(KEY_NAME, "")
        set(value) = pref.edit().putString(KEY_NAME, value).apply()

    fun setString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return pref.getString(key, "")
    }

}