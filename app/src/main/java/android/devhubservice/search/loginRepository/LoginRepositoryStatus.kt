package android.devhubservice.search.loginRepository

import android.service.models.LoginModel

sealed class LoginRepositoryStatus{
    object NotFound: LoginRepositoryStatus()
    data class Success(val loginModel: LoginModel): LoginRepositoryStatus()
    data class Error(val error: Throwable): LoginRepositoryStatus()
}