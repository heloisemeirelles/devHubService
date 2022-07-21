package android.devhubservice.viewModel.favorite.list

import android.devhubservice.search.favoriteListsRepository.FavoriteListsRepositoryImpl
import android.service.models.FavoriteListsModel
import android.service.models.LoggedModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteListViewModel(private val favoriteListsRepositoryImpl: FavoriteListsRepositoryImpl): ViewModel() {
    lateinit var listData: List<FavoriteListsModel>

    fun addList(favoriteListsModel: FavoriteListsModel) = viewModelScope.launch {
        favoriteListsRepositoryImpl.addList(favoriteListsModel)

    }

    fun deleteListByName(favoriteListsModel: FavoriteListsModel) = viewModelScope.launch {
        favoriteListsRepositoryImpl.deleteListByName(favoriteListsModel)

    }

    suspend fun getAllLists() {
        listData = favoriteListsRepositoryImpl.getAllLists()
    }

    suspend fun getListsByLogin(login:String) {
        listData = favoriteListsRepositoryImpl.getListsByLogin(login)
    }


}