package android.devhubservice.search.loggedRepository

import android.service.database.logged.LoggedDao
import android.service.models.LoggedModel
import android.service.models.LoginModel

class LoggedRepositoryImpl(private val loggedDao: LoggedDao) {
    suspend fun addUser(loggedModel: LoggedModel) = loggedDao.addUser(loggedModel)
    suspend fun deleteUser(loggedModel: LoggedModel) = loggedDao.deleteUser(loggedModel)
    suspend fun getUserByLogin() = loggedDao.getUserByLogin()
}