package au.com.roadhuose.spacepaldriverpicker

import android.app.Application
import android.content.Intent
import android.util.Log
import au.com.roadhuose.spacepaldriverpicker.event.LoginFailEvent
import au.com.roadhuose.spacepaldriverpicker.util.PreferenceUtil
import com.roadhourse.spacepal.ui.login.LoginActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SpacePalApplication : Application(), AppLifecycleHandler.LifeCycleDelegate {
    private var lifeCycleHandler: AppLifecycleHandler? = null
    var isForeground = true
        private set

    override fun onCreate() {
        super.onCreate()
        insta = this
        EventBus.getDefault().register(this)
        Realm.init(this)
        lifeCycleHandler = AppLifecycleHandler(this)
        registerLifecycleHandler(lifeCycleHandler!!)
        val realmConfiguration = RealmConfiguration.Builder()
                .name("Realm.SpacePal")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: LoginFailEvent) {
        if (PreferenceUtil.getInstance(this).account.isSignIn()) {
            logout()
        }
    }


    fun logout() {

        PreferenceUtil.getInstance(this).saveAccount(null)
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onAppBackgrounded() {
        isForeground = false
        Log.d("SpacePalApplication", " App in background")
    }

    override fun onAppForegrounded() {
        isForeground = true
        Log.d("SpacePalApplication", " App in foreground")
    }

    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }

    companion object {
        private var insta: SpacePalApplication? = null

        fun getInstance(): SpacePalApplication {
            return if (insta == null) SpacePalApplication() else insta!!
        }
    }
}
