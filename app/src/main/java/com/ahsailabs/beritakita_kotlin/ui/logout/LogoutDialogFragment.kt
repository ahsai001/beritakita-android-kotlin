package com.ahsailabs.beritakita_kotlin.ui.logout

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ahsailabs.beritakita_kotlin.utils.InfoUtil
import com.ahsailabs.beritakita_kotlin.utils.SessionUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by ahmad s on 06/05/21.
 */
class LogoutDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout Confirmation")
            .setMessage("Are you sure?")
            .setPositiveButton("logout") { dialogInterface, i ->
                SessionUtil.logout(requireContext())
                InfoUtil.showToast(context, "logout anda berhasil")
            }
            .setNegativeButton(
                "cancel"
            ) { dialogInterface, i -> }

        return dialogBuilder.create()
    }
}