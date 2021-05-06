package com.ahsailabs.beritakita_kotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.IntRange
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ahsailabs.beritakita_kotlin.utils.DialogUtil.showDialog2Option
import java.util.*

/**
 * Created by ahsai on 12/7/2017.
 */
class PermissionUtil(
    private var activityOrFragment: Any?,
    private var requestCode: Int,
    private var taskWillDo: Runnable?,
    private var taskIfDenied: Runnable?
) {
    private val context: Context?
        get() {
            var context: Context? = null
            if (activityOrFragment != null) {
                if (activityOrFragment is Activity) {
                    context = activityOrFragment as Activity
                } else if (activityOrFragment is Fragment) {
                    context = (activityOrFragment as Fragment).activity
                }
            }
            return context
        }

    private fun arePermissionsGranted(
        showDialogInit: Boolean,
        initTitle: String?,
        initBody: String?,
        vararg permissions: String
    ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activityOrFragment != null) {
            val needRequested = ArrayList<String>()
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        context!!,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    needRequested.add(permission)
                }
            }
            if (needRequested.size > 0) {
                if (showDialogInit) {
                    showDialog2Option(
                        context,
                        initTitle ?: "Permission",
                        initBody ?: "we need all these permissions to run properly",
                        "OK",
                        { //need request permission
                            val needRequestedPermission = needRequested.toTypedArray()
                            requestPermisssion(
                                activityOrFragment,
                                needRequestedPermission,
                                requestCode
                            )
                        },
                        "Cancel"
                    ) { taskIfDenied?.run() }
                } else {
                    //need request permission
                    val needRequestedPermission = needRequested.toTypedArray()
                    requestPermisssion(activityOrFragment, needRequestedPermission, requestCode)
                }
                return false
            }
        }
        return true
    }

    private fun requestPermisssion(
        activityOrFragment: Any?,
        needRequestedPermission: Array<String>, @IntRange(from = 0) requestCode: Int
    ) {
        if (activityOrFragment is Activity) {
            ActivityCompat.requestPermissions(
                activityOrFragment,
                needRequestedPermission,
                requestCode
            )
        } else if (activityOrFragment is Fragment) {
            activityOrFragment.requestPermissions(needRequestedPermission, requestCode)
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == this.requestCode) {
            // If request is cancelled, the result arrays are empty.
            val deniedPermissions = ArrayList<String?>()
            val grantedPermissions = ArrayList<String?>()
            if (grantResults.size > 0) {
                //partial cancelled
                var i = 0
                for (result in grantResults) {
                    if (result == PackageManager.PERMISSION_DENIED) {
                        deniedPermissions.add(permissions[i])
                    } else if (result == PackageManager.PERMISSION_GRANTED) {
                        grantedPermissions.add(permissions[i])
                    }
                    i++
                }
            } else {
                //all cancelled
            }

            if (grantedPermissions.size == permissions.size) {
                //do task
                taskWillDo?.run()
            } else {
                //there is some denied
                var isAnyNeverAskAgainChecked = false
                if (activityOrFragment is Activity) {
                    for (deniedPermission in deniedPermissions) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                                (activityOrFragment as Activity?)!!,
                                deniedPermission!!
                            )
                        ) {
                            isAnyNeverAskAgainChecked = true
                        }
                    }
                } else if (activityOrFragment is Fragment) {
                    for (deniedPermission in deniedPermissions) {
                        if (!(activityOrFragment as Fragment).shouldShowRequestPermissionRationale(
                                deniedPermission!!
                            )
                        ) {
                            isAnyNeverAskAgainChecked = true
                        }
                    }
                }
                if (isAnyNeverAskAgainChecked) {
                    showDialog2Option(
                        context,
                        "Permission",
                        "Sorry, because you already check never ask again and we need all these permissions to run properly, please enable permission in this application setting",
                        "Cancel",
                        { },
                        "Setting"
                    ) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", context!!.packageName, null)
                        intent.data = uri
                        context!!.startActivity(intent)
                    }
                }
                taskIfDenied?.run()
            }
        }
    }

    companion object {
        fun checkPermissionAndGo(
            activityOrFragment: Any?,
            requestCode: Int,
            showDialogInit: Boolean,
            initTitle: String?,
            initBody: String?,
            taskWillDo: Runnable?,
            taskIfDenied: Runnable?,
            vararg permissions: String?
        ): PermissionUtil? {
            var permissionUtil: PermissionUtil? = null
            if (permissions.isNotEmpty()) {
                permissionUtil =
                    PermissionUtil(activityOrFragment, requestCode, taskWillDo, taskIfDenied)
                if (permissionUtil.arePermissionsGranted(
                        showDialogInit,
                        initTitle,
                        initBody,
                        *permissions as Array<out String>
                    )
                ) {
                    taskWillDo?.run()
                }
            } else {
                taskWillDo?.run()
            }
            return permissionUtil
        }

        fun checkPermissionAndGo2(
            activityOrFragment: Any?,
            requestCode: Int,
            taskWillDo: Runnable?,
            taskIfDenied: Runnable?,
            vararg permissions: String?
        ): PermissionUtil? {
            return checkPermissionAndGo(
                activityOrFragment,
                requestCode,
                true,
                null,
                null,
                taskWillDo,
                taskIfDenied,
                *permissions
            )
        }

        fun checkPermissionAndGo(
            activityOrFragment: Any?,
            requestCode: Int,
            taskWillDo: Runnable?,
            vararg permissions: String?
        ): PermissionUtil? {
            return checkPermissionAndGo2(
                activityOrFragment,
                requestCode,
                taskWillDo,
                null,
                *permissions
            )
        }
    }
}