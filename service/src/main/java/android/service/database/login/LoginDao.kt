package android.service.database.login

import android.service.models.LoginModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(loginModel: LoginModel)

    @Query("SELECT * FROM login_table WHERE login = :login")
    suspend fun getUserByLogin(login: String): List<LoginModel>

    @Query("SELECT EXISTS(SELECT * FROM login_table WHERE login = :login)")
    suspend fun checkIfUsernameExists(login: String) : Boolean

    @Delete
    suspend fun deleteUserByLogin(loginModel: LoginModel)

}