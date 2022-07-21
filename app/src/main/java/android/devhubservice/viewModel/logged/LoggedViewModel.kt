package android.devhubservice.viewModel.logged

import android.devhubservice.search.loggedRepository.LoggedRepositoryImpl
import android.service.models.LoggedModel
import android.service.models.LoginModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoggedViewModel(private val loggedRepositoryImpl: LoggedRepositoryImpl): ViewModel() {
    lateinit var loginData: List<LoggedModel>

    fun addUser(loggedModel: LoggedModel) = viewModelScope.launch {
        loggedRepositoryImpl.addUser(loggedModel)
    }

    fun deleteUser(loggedModel: LoggedModel) = viewModelScope.launch {
        loggedRepositoryImpl.deleteUser(loggedModel)
    }

    suspend fun getUserByLogin() {
        loginData = loggedRepositoryImpl.getUserByLogin()
    }
}