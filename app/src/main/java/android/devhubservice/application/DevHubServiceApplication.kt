package android.devhubservice.application

import android.app.Application
import android.devhubservice.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DevHubServiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DevHubServiceApplication)
            modules(
                loginDb,
                loginRepositoryImpl,
                devHubServiceModule,
                userModule,
                repoModule,
                loggedModule,
                loggedDb,
                searchUser,
                favoriteListModule,
                favoriteListDB,
                favoriteRepoDB,
                favoriteModule
            )
        }
    }
}