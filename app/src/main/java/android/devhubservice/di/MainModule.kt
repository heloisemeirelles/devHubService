package android.devhubservice.di

import android.app.Application
import android.devhubservice.search.favoriteListsRepository.FavoriteListsRepositoryImpl
import android.devhubservice.search.favoriteRepoRepository.FavoriteRepoRepositoryImpl
import android.devhubservice.search.loggedRepository.LoggedRepositoryImpl
import android.devhubservice.search.loginRepository.LoginRepositoryImpl
import android.devhubservice.search.repoRepository.RepoRepositoryImpl
import android.devhubservice.search.searchUserRepositoryStatus.SearchUserRepositoryImpl
import android.devhubservice.search.userRepository.UserRepositoryImpl
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.devhubservice.viewModel.logged.LoggedViewModel
import android.devhubservice.viewModel.login.LoginViewModel
import android.devhubservice.viewModel.repo.RepoViewModel
import android.devhubservice.viewModel.user.SearchViewModel
import android.devhubservice.viewModel.user.UserViewModel
import android.service.apiService.api.DevHubService
import android.service.constants.Constants.BASE_URL
import android.service.database.favorites.list.FavoriteListsDao
import android.service.database.favorites.list.FavoriteListsDatabase
import android.service.database.favorites.repo.FavoriteDao
import android.service.database.favorites.repo.FavoriteDatabase
import android.service.database.logged.LoggedDao
import android.service.database.logged.LoggedDatabase
import android.service.database.login.LoginDao
import android.service.database.login.LoginDatabase
import android.service.models.FavoriteListsModel
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loginDb = module {
    fun provideLoginDatabase(application: Application): LoginDatabase {
        return Room.databaseBuilder(application, LoginDatabase::class.java, "LOGINDB").build()
    }

    fun provideLoginDao(database: LoginDatabase): LoginDao {
        return database.loginDao()
    }

    single{provideLoginDatabase(androidApplication())}
    single { provideLoginDao(get()) }

}

val devHubServiceModule = module {
    single(named("BASE_URL")) {
        BASE_URL
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(DevHubService::class.java)
    }
}

val loginRepositoryImpl = module {
    factory <LoginRepositoryImpl> {
        LoginRepositoryImpl(get())
    }

    viewModel {
        LoginViewModel(get())
    }
}

val userModule = module {
    factory {
        UserRepositoryImpl(get())
    }
    viewModel{
        UserViewModel(
            userRepositoryImpl = get()
        )
    }
}

val repoModule = module {
    factory {
        RepoRepositoryImpl(get())
    }
    viewModel {
        RepoViewModel(
            repoRepositoryImpl = get()
        )
    }
}

val searchUser = module {
    factory {
        SearchUserRepositoryImpl(get())
    }
    viewModel {
        SearchViewModel(
            searchUserRepositoryImpl = get()
        )
    }
}

val loggedDb = module {
    fun provideLoggedDatabase(application: Application): LoggedDatabase {
        return Room.databaseBuilder(application, LoggedDatabase::class.java, "LOGGEDDB").build()
    }

    fun provideLoggedDao(database: LoggedDatabase): LoggedDao {
        return database.loggedDao()
    }

    single{provideLoggedDatabase(androidApplication())}
    single { provideLoggedDao(get()) }

}

val loggedModule = module {
    factory {
        LoggedRepositoryImpl(get())
    }
    viewModel {
        LoggedViewModel(
            loggedRepositoryImpl = get()
        )
    }
}


val favoriteListDB = module {
    fun provideFavoriteListDatabase(application: Application): FavoriteListsDatabase {
        return Room.databaseBuilder(application, FavoriteListsDatabase::class.java, "FAVORITELISTDB").build()
    }

    fun provideFavoriteListsDao(database: FavoriteListsDatabase): FavoriteListsDao {
        return database.favoriteListsDao()
    }

    single{provideFavoriteListDatabase(androidApplication())}
    single { provideFavoriteListsDao(get()) }

}

val favoriteListModule = module {
    factory {
        FavoriteListsRepositoryImpl(get())
    }
    viewModel {
        FavoriteListViewModel(
            favoriteListsRepositoryImpl = get()
        )
    }
}

val favoriteRepoDB = module {
    fun provideFavoriteDatabase(application: Application): FavoriteDatabase {
        return Room.databaseBuilder(application, FavoriteDatabase::class.java, "FAVORITEDB").build()
    }

    fun provideFavoriteDao(database: FavoriteDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    single{provideFavoriteDatabase(androidApplication())}
    single { provideFavoriteDao(get()) }

}

val favoriteModule = module {

    factory {
        FavoriteRepoRepositoryImpl(get())
    }
    viewModel {
        FavoriteRepoViewModel(
            favoriteRepoRepositoryImpl = get()
        )
    }
}


