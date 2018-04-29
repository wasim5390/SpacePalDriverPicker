package au.com.roadhuose.spacepaldriverpicker

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle

public class AppLifecycleHandler(private val lifeCycleDelegate: LifeCycleDelegate) : Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private var appInForeground = false


    override fun onActivityCreated(activity: Activity, bundle: Bundle) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        if (!appInForeground) {
            appInForeground = true
            lifeCycleDelegate.onAppForegrounded()
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            appInForeground = false
            lifeCycleDelegate.onAppBackgrounded()
        }
    }

    override fun onConfigurationChanged(configuration: Configuration) {

    }

    override fun onLowMemory() {

    }

    public interface LifeCycleDelegate {
        fun onAppBackgrounded()
        fun onAppForegrounded()
    }
}
