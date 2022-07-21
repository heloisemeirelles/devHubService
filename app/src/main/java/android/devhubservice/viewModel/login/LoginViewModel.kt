package android.devhubservice.viewModel.login

import android.devhubservice.search.loginRepository.LoginRepositoryImpl
import android.devhubservice.search.loginRepository.LoginRepositoryStatus
import android.service.models.LoginModel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class LoginViewModel(private val loginRepositoryImpl: LoginRepositoryImpl) : ViewModel() {
    lateinit var loginData: List<LoginModel>

    fun addUser(loginModel: LoginModel) = viewModelScope.launch {
        loginRepositoryImpl.addUser(loginModel)
    }

    suspend fun getUserByLogin(login: String) {
        loginData = loginRepositoryImpl.getUserByLogin(login)
    }

    fun deleteUserByLogin(loginModel: LoginModel) = viewModelScope.launch {
        loginRepositoryImpl.deleteUserByLogin(loginModel)
    }

    fun verifyIfFieldIsFilled(field: String): Boolean {
        return field != ""
    }

    fun verifyIdPasswordsMatch(newPassword: String, confirmPassword: String): Boolean{
        return newPassword == confirmPassword
    }
}
