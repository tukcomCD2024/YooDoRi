package kr.ac.tukorea.whereareu

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kr.ac.tukorea.whereareu.util.network.NetworkConnectionChecker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhereAreUApplication: Application(), DefaultLifecycleObserver {
    override fun onCreate() {
        super<Application>.onCreate()
        context = applicationContext
        networkConnectionChecker = NetworkConnectionChecker(context)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "location",
                "Location",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        networkConnectionChecker.unregister()
        super.onStop(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        networkConnectionChecker.register()
        super.onStart(owner)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getString(@StringRes stringResId: Int): String {
            return context.getString(stringResId)
        }

        private lateinit var networkConnectionChecker: NetworkConnectionChecker
        fun isOnline() = networkConnectionChecker.isOnline()

        private lateinit var instance: WhereAreUApplication
        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }
}