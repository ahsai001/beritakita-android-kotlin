package com.ahsailabs.beritakita_kotlin.utils

import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AlertDialog

/**
 * Created by ahmad s on 22/08/20.
 */
object DialogUtil {
    @JvmStatic
    fun showDialog2Option(
        context: Context?,
        title: String?,
        msg: String?,
        strOption1: String?,
        option1: Runnable?,
        strOption2: String?,
        option2: Runnable?
    ): AlertDialog {
        return showDialog3OptionWithIcon(
            context,
            null,
            title,
            msg,
            strOption1,
            option1,
            strOption2,
            option2,
            null,
            null
        )
    }

    fun showDialog3OptionWithIcon(
        context: Context?,
        icon: Drawable?,
        title: String?,
        msg: String?,
        strOption1: String?,
        option1: Runnable?,
        strOption2: String?,
        option2: Runnable?,
        strOption3: String?,
        option3: Runnable?
    ): AlertDialog {
        return showDialog3OptionWithIcon(
            context,
            icon,
            title,
            msg,
            strOption1,
            option1,
            true,
            strOption2,
            option2,
            true,
            strOption3,
            option3,
            true
        )
    }

    fun fromHtml(html: String?): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    fun showDialog3OptionWithIcon(
        context: Context?,
        icon: Drawable?,
        title: String?,
        msg: String?,
        strOption1: String?,
        option1: Runnable?,
        dismissByOption1: Boolean,
        strOption2: String?,
        option2: Runnable?,
        dismissByOption2: Boolean,
        strOption3: String?,
        option3: Runnable?,
        dismissByOption3: Boolean
    ): AlertDialog {
        val builder = AlertDialog.Builder(
            context!!
        )
        builder.setMessage(fromHtml(msg)).setCancelable(false)
        if (icon != null) {
            builder.setIcon(icon)
        }
        if (strOption2 != null) {
            builder.setNeutralButton(strOption2, null)
        }
        if (strOption1 != null) {
            builder.setPositiveButton(strOption1, null)
        }
        if (strOption3 != null) {
            builder.setNegativeButton(strOption3, null)
        }
        val alert = builder.create()
        alert.setTitle(title)
        alert.show()

        //set custom button
        if (strOption2 != null) {
            alert.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener {
                option2?.run()
                if (dismissByOption2) alert.dismiss()
            }
        }
        if (strOption1 != null) {
            alert.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                option1?.run()
                if (dismissByOption1) alert.dismiss()
            }
        }
        if (strOption3 != null) {
            alert.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
                option3?.run()
                if (dismissByOption3) alert.dismiss()
            }
        }
        return alert
    }
}