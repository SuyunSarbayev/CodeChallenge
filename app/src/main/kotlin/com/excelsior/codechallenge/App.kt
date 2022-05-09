package com.excelsior.codechallenge

import android.app.Application
import com.excelsior.codechallenge.infrastructure.di.InjectionModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)

            modules(InjectionModules.modules)
        }

        val loggingTree = Timber.DebugTree()
            .takeIf { BuildConfig.DEBUG }
            ?: object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    // no-op
                }
            }

        Timber.plant(loggingTree)
    }
}