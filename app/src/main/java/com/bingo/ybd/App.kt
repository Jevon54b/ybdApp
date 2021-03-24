package com.bingo.ybd

import android.app.Application
import android.content.ContextWrapper
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


lateinit var mApplication: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        //启动器进行异步初始化
//        TaskDispatcher.init(this)
//        TaskDispatcher.createInstance()
//            .addTask(InitKoInTask())
//        //    .addTask(InitLiveEventBusTask())
//           // .addTask(InitSmartRefreshLayoutTask())
//            .start()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            androidFileProperties()
            modules(appModule)
        }

    }
}

object AppContext : ContextWrapper(mApplication)//ContextWrapper对Context上下文进行包装(装饰者模式)
