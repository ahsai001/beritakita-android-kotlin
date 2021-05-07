package com.ahsailabs.beritakita_kotlin.utils

import android.R
import androidx.annotation.ColorRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import java.lang.ref.WeakReference

/**
 * Created by ahsai on 7/18/2018.
 */
class SwipeRefreshLayoutUtil {
    private var swipeRefreshLayoutRef: WeakReference<SwipeRefreshLayout>? = null
    private var refreshAction: Runnable? = null
    private fun doRefresh(refreshAction: Runnable?) {
        refreshAction?.run()
        //new Handler(Looper.getMainLooper()).post(refreshAction);
    }

    fun setColorSchemeResources(@ColorRes vararg colorResIds: Int) {
        if (swipeRefreshLayoutRef != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            swipeRefreshLayout?.setColorSchemeResources(*colorResIds)
        }
    }

    fun setColorSchemeColors(vararg colors: Int) {
        if (swipeRefreshLayoutRef != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            swipeRefreshLayout?.setColorSchemeColors(*colors)
        }
    }

    fun setEnabled(enabled: Boolean) {
        if (swipeRefreshLayoutRef != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isEnabled = enabled
            }
        }
    }

    fun setProgressBackgroundColorSchemeColor(color: Int) {
        if (swipeRefreshLayoutRef != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            swipeRefreshLayout?.setProgressBackgroundColorSchemeColor(color)
        }
    }

    fun setProgressBackgroundColorSchemeResource(colorRes: Int) {
        if (swipeRefreshLayoutRef != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            swipeRefreshLayout?.setProgressBackgroundColorSchemeResource(colorRes)
        }
    }

    fun refreshNow(): Boolean {
        if (swipeRefreshLayoutRef != null && refreshAction != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isRefreshing = true
                doRefresh(refreshAction)
                return true
            }
        }
        return false
    }

    fun refreshDone() {
        if (swipeRefreshLayoutRef != null) {
            val swipeRefreshLayout = swipeRefreshLayoutRef?.get()
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    companion object {
        fun init(swipeRefreshLayout: SwipeRefreshLayout?, refreshAction: Runnable?): SwipeRefreshLayoutUtil {
            val swipeRefreshLayoutUtils = SwipeRefreshLayoutUtil()
            if (swipeRefreshLayout != null) {
                swipeRefreshLayoutUtils.swipeRefreshLayoutRef = WeakReference(swipeRefreshLayout)
                swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
                    if (swipeRefreshLayoutUtils.refreshAction != null) {
                        swipeRefreshLayoutUtils.doRefresh(swipeRefreshLayoutUtils.refreshAction)
                    }
                })
                swipeRefreshLayout.setColorSchemeResources(
                        R.color.holo_blue_bright,
                        R.color.holo_green_light,
                        R.color.holo_orange_light,
                        R.color.holo_red_light)
            }
            swipeRefreshLayoutUtils.refreshAction = refreshAction
            return swipeRefreshLayoutUtils
        }
    }
}