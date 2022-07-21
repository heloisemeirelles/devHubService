package android.devhubservice.viewModel.favorite.repo

import android.devhubservice.search.favoriteRepoRepository.FavoriteRepoRepositoryImpl
import android.service.models.FavoriteListsModel
import android.service.models.FavoriteRepoModel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteRepoViewModel(private val favoriteRepoRepositoryImpl: FavoriteRepoRepositoryImpl) :
    ViewModel() {
    lateinit var listData: List<FavoriteRepoModel>
    fun addFavorite(favoriteRepoModel: FavoriteRepoModel) = viewModelScope.launch {
        Log.v("mytag", "no add")
        favoriteRepoRepositoryImpl.addFavorite(favoriteRepoModel)
    }

    fun deleteFavorite(favoriteRepoModel: FavoriteRepoModel) = viewModelScope.launch {
        Log.v("mytag", "no delete")
        favoriteRepoRepositoryImpl.deleteFavorite(favoriteRepoModel)
    }

    suspend fun getAllByList(login: String, listName: String) {
        listData = favoriteRepoRepositoryImpl.getAllByList(login, listName)
    }

    suspend fun getFavoriteByUser(login: String, repoUsername: String) {
        listData = favoriteRepoRepositoryImpl.getFavoriteByUser(login, repoUsername)
    }

    suspend fun getAll() {
        listData = favoriteRepoRepositoryImpl.getAll()
    }
}