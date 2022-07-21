package android.service.database.logged

import android.service.models.LoggedModel
import android.service.models.LoginModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoggedModel::class], version = 1)
abstract class LoggedDatabase: RoomDatabase() {
    abstract fun loggedDao(): LoggedDao
}