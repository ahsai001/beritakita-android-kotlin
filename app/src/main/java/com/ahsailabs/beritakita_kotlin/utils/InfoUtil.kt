package com.ahsailabs.beritakita_kotlin.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Created by ahmad s on 2019-09-26.
 */
object InfoUtil {
    fun showToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showSnackBar(view: View?, message: String?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG).show()
    }
}