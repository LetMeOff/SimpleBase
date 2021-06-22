package com.zjh.simplebase.manager

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.zjh.simplebase.util.SimpleLog
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 界面管理类
 *
 * @author zjh
 * on 2021/4/15
 */
object ActivityManager {

    private const val TAG = "ActivityManager"

    private val list = mutableListOf<Activity>()

    var uiHandler: Handler = Handler(Looper.getMainLooper())
        private set

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object :
                Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                addActivity(activity)
                printActivityList()
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                lastLiteCycleState.resumed()
                switchToForeground(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                lastLiteCycleState.paused()
                checkBackgroundState(activity)
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                removeActivity(activity)
                printActivityList()
            }

        })
    }

    val curActivity: Activity?
        get() = list.lastOrNull()

    val activitys: List<Activity>
        get() = list.toList()

    /**返回List，存在则List不为空**/
    fun <T : Activity> filter(clazz: Class<T>): List<T> {
        return list.filter {
            it::class.java.name == clazz.name
        }.map { it as T }
    }

    /**
     * 判断是否有某个activity
     */
    fun hasActivity(cls: Class<out Activity?>): Boolean {
        var has = false
        for (activity in list) {
            if (activity.javaClass == cls) {
                has = true
                break
            }
        }
        return has
    }

    /**
     * 关闭某个activity
     */
    fun finishActivity(cls: Class<out Activity?>) {
        for (activity in list) {
            if (activity.javaClass == cls) {
                activity.finish()
                break
            }
        }
    }

    /**关闭所有页面，并在关闭后执行[block]函数**/
    fun finishAll(block: ((activity: Activity?) -> Unit)? = null) {
        val curA = curActivity
        list.forEach {
            it.finish()
        }
        block?.invoke(curA)
    }

    private fun addActivity(activity: Activity) {
        list.add(activity)
    }

    private fun removeActivity(activity: Activity) {
        list.remove(activity)
    }

    private fun printActivityList() {
        SimpleLog.d(TAG) {
            list.fold("") { result, activity ->
                "$result${if (result.isEmpty()) "" else " -> "}${activity::class.java.simpleName}"
            }
        }
    }

    private data class LastLiteCycleState(var isResumed: Boolean, var isPaused: Boolean) {
        fun resumed() {
            isResumed = true
            isPaused = false
        }

        fun paused() {
            isResumed = false
            isPaused = true
        }
    }

    /**是否在前台**/
    var isForeground: Boolean = false
        private set
    private val listeners: MutableList<ForegroundListener> =
            CopyOnWriteArrayList<ForegroundListener>()
    private val lastLiteCycleState: LastLiteCycleState = LastLiteCycleState(
            isResumed = false,
            isPaused = false
    )

    private var runnableBackground: Runnable? = null

    fun addForegroundListener(listener: ForegroundListener) {
        listeners.add(listener)
    }

    fun removeForegroundListener(listener: ForegroundListener) {
        listeners.remove(listener)
    }

    private fun switchToForeground(activity: Activity) {
        val isBackground = !isForeground
        isForeground = true
        runnableBackground?.apply {
            uiHandler.removeCallbacks(this)
        }
        if (isBackground) {
            SimpleLog.d(TAG, "切换到前台：当前页面是${activity::class.java.name}")
            listeners.forEach {
                it.onSwitchToForeground(activity)
            }
        }
    }

    /**检查是否切换到后台**/
    private fun checkBackgroundState(activity: Activity) {
        runnableBackground = Runnable {
            if (isForeground && lastLiteCycleState.isPaused) {
                isForeground = false
                SimpleLog.d(TAG, "切换到后台：从${activity::class.java.name}页面中切换到后台")
                listeners.forEach {
                    it.onSwitchToBackground(activity)
                }
            }
        }
        uiHandler.postDelayed(runnableBackground!!, 500)
    }

    interface ForegroundListener {
        /**当APP从后台切换到前台时被调用**/
        fun onSwitchToForeground(activity: Activity)

        /**当APP从前台切换到后台时被调用**/
        fun onSwitchToBackground(activity: Activity)
    }
}