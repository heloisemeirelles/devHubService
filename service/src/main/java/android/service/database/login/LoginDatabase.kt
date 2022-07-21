package android.service.database.login

import android.service.models.LoginModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginModel::class], version = 1)
abstract class LoginDatabase: RoomDatabase() {
    abstract fun loginDao(): LoginDao
}