package android.devhubservice.search.searchUserRepositoryStatus

import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.service.apiService.api.DevHubService

class SearchUserRepositoryImpl(private val devHubService: DevHubService) {
    suspend fun getUserByQuery(username: String): SearchUserRepositoryStatus {
        return try {
            val response = devHubService.getUserByQuery(username)
            if (response != null) {
                SearchUserRepositoryStatus.Success(response)
            } else {
                SearchUserRepositoryStatus.NotFound
            }
        } catch (e: Exception) {
            SearchUserRepositoryStatus.Error(e)
        }
    }
}