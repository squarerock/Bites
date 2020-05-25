package squarerock.bites

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import squarerock.bites.di.appModule
import squarerock.bites.di.viewModelModules

class Bites: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Bites)
            modules(listOf(appModule, viewModelModules))
        }
    }
}