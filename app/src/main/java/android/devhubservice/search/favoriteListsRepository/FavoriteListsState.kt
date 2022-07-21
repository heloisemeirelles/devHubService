package android.devhubservice.search.favoriteListsRepository

import android.service.models.FavoriteListsModel

sealed class FavoriteListsState {
    object NotFound: FavoriteListsState()
    data class Success(val favoriteListModel: List<FavoriteListsModel>): FavoriteListsState()
    data class Error(val error: Throwable): FavoriteListsState()
}