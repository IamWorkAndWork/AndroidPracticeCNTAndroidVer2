package app.cntandroidver2.workshop.utils

import android.content.Context
import android.widget.Toast
import app.cntandroidver2.workshop.R

object Singleton {

    fun say(context: Context) {
        Toast.makeText(context, context.getString(R.string.i_am_singleton), Toast.LENGTH_SHORT)
            .show()
    }
}