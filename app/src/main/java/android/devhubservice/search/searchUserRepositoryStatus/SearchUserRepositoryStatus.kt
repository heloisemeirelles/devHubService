package android.devhubservice.search.searchUserRepositoryStatus

import android.service.models.UsersSearchResponse

sealed class SearchUserRepositoryStatus {
    object NotFound: SearchUserRepositoryStatus()
    data class Success(val searchResponse: UsersSearchResponse): SearchUserRepositoryStatus()
    data class Error(val error: Throwable): SearchUserRepositoryStatus()
}