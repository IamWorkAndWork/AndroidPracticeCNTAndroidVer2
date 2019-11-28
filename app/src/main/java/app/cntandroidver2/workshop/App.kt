package app.cntandroidver2.workshop

import android.app.Application

class App : Application() {

//    private lateinit var appComponent: AppComponent;

    override fun onCreate() {
        super.onCreate()

//        appComponent = DaggerAppComponent.builder().apiModule(ApiModule())
//            .build()
    }

//    fun getComponent(): AppComponent {
//        return appComponent
//    }


}