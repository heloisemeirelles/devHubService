package android.service.database.favorites.list

import android.service.models.FavoriteListsModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteListsModel::class], version = 1)
abstract class FavoriteListsDatabase: RoomDatabase() {
    abstract fun favoriteListsDao(): FavoriteListsDao
}