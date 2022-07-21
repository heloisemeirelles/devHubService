package android.devhubservice.search.userRepository

import android.service.apiService.api.DevHubService

class UserRepositoryImpl(private val devHubService: DevHubService) {
    suspend fun getUserByUserName(username: String): UserRepositoryStatus {
        return try {
            val response = devHubService.getUserByUsername(username)
            if(response != null){
                UserRepositoryStatus.Success(response)
            } else {
                UserRepositoryStatus.NotFound
            }
        } catch (e: Throwable){
            UserRepositoryStatus.Error(e)
        }
    }


}