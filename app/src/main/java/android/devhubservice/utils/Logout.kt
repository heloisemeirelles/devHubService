package android.devhubservice.utils

import android.devhubservice.viewModel.logged.LoggedViewModel
import android.devhubservice.viewModel.login.LoginViewModel
import android.service.models.LoggedModel
import android.service.models.LoginModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Logout(
    private val loggedViewModel: LoggedViewModel,
    private val loginViewModel: LoginViewModel
) {


    fun deleteUserFromDB(username: String) {
        lateinit var loggedModel: LoggedModel
        loginViewModel.viewModelScope.launch {
            loginViewModel.getUserByLogin(username)
            loginViewModel.loginData.let {
                when (it.lastIndex >= 0) {
                    true -> {
                        loggedModel = LoggedModel(it[0].login)
                    }
                    false -> {
                        loggedModel = LoggedModel("")
                    }
                }
            }
        }

        loggedViewModel.viewModelScope.launch {
            loggedViewModel.deleteUser(loggedModel)
        }
    }
}