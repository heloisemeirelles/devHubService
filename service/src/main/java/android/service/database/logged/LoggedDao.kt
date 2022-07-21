package android.service.database.logged

import android.service.models.LoggedModel
import android.service.models.LoginModel
import androidx.room.*

@Dao
interface LoggedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(loggedModel: LoggedModel)

    @Delete
    suspend fun deleteUser(loggedModel: LoggedModel)

    @Query("SELECT * FROM logged_table")
    suspend fun getUserByLogin(): List<LoggedModel>
}