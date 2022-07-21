package android.devhubservice.search.repoRepository

import android.service.models.RepoModel

sealed class RepoRepositoryStatus {
    object NotFound: RepoRepositoryStatus()
    data class Success(val repoList: List<RepoModel>): RepoRepositoryStatus()
    data class Error(val error: Throwable): RepoRepositoryStatus()
}