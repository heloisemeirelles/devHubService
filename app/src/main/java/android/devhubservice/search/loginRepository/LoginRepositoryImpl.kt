package android.devhubservice.search.loginRepository

import android.service.database.login.LoginDao
import android.service.database.login.LoginDatabase
import android.service.models.LoginModel
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class LoginRepositoryImpl(private val loginDao: LoginDao) {
    suspend fun addUser(loginModel: LoginModel) = loginDao.addUser(loginModel)

    suspend fun getUserByLogin(login: String) = loginDao.getUserByLogin(login)

    suspend fun checkIfUsernameExists(login: String) = loginDao.checkIfUsernameExists(login)

    suspend fun deleteUserByLogin(loginModel: LoginModel) = loginDao.deleteUserByLogin(loginModel)
}