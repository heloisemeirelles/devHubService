package android.devhubservice.search.repoRepository

import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.service.apiService.api.DevHubService

class RepoRepositoryImpl(private val devHubService: DevHubService) {
    suspend fun getReposByUsername(username: String): RepoRepositoryStatus {
        return try {
            val response = devHubService.getReposByUsername(username)
            if (response != null) {
                RepoRepositoryStatus.Success(response)
            } else {
                RepoRepositoryStatus.NotFound
            }
        } catch (e: Exception) {
            RepoRepositoryStatus.Error(e)
        }
    }
}