package android.service.database.favorites.repo

import android.service.database.login.LoginDao
import android.service.models.FavoriteRepoModel
import android.service.models.LoginModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteRepoModel::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}