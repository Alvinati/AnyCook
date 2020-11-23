package com.porto.anycook

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.porto.anycook.injection.DaggerApplicationComponent
import com.porto.anycook.injection.DaggerCoreComponent
import com.porto.anycook.injection.Injectable
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjection

class MainApplication : DaggerApplication(),  Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .coreComponenet(DaggerCoreComponent.builder().context(this).build())
            .build()
    }


    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
        handleActivity(activity)
    }

    private fun handleActivity(activity: Activity) {
        AndroidInjection.inject(activity)

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {

                    override fun onFragmentPreAttached(
                        fm: FragmentManager,
                        f: Fragment,
                        context: Context
                    ) {
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                        super.onFragmentPreAttached(fm, f, context)
                    }

                }, true)
        }
    }
}