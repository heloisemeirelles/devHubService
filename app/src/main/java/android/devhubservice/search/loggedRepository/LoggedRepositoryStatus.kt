package android.devhubservice.search.loggedRepository

import android.service.models.LoginModel

sealed class LoggedRepositoryStatus {
    object NotFound: LoggedRepositoryStatus()
    data class Success(val loggedModel: LoginModel): LoggedRepositoryStatus()
    data class Error(val error: Throwable): LoggedRepositoryStatus()
}