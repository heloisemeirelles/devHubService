package android.devhubservice.search.userRepository

import android.service.models.UserModel


sealed class UserRepositoryStatus {
    object NotFound: UserRepositoryStatus()
    data class Success(val userModel: UserModel): UserRepositoryStatus()
    data class Error(val error: Throwable): UserRepositoryStatus()
}