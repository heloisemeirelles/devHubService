package android.devhubservice.viewModel.user

import android.devhubservice.search.searchUserRepositoryStatus.SearchUserRepositoryImpl
import android.devhubservice.search.searchUserRepositoryStatus.SearchUserRepositoryStatus
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUserRepositoryImpl: SearchUserRepositoryImpl): ViewModel() {
    val status = MutableLiveData<SearchUserRepositoryStatus>()

    fun getUserByQuery(username: String){
        viewModelScope.launch {
            when (val response = searchUserRepositoryImpl.getUserByQuery(username)){
                is SearchUserRepositoryStatus.Success -> {
                    status.value = SearchUserRepositoryStatus.Success(response.searchResponse)
                }
                is SearchUserRepositoryStatus.NotFound -> {
                    status.value = SearchUserRepositoryStatus.NotFound
                }
                is SearchUserRepositoryStatus.Error -> {
                    status.value = SearchUserRepositoryStatus.Error(response.error)
                }
            }
        }
    }
}