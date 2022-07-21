package android.devhubservice.viewModel.repo

import android.devhubservice.search.repoRepository.RepoRepositoryImpl
import android.devhubservice.search.repoRepository.RepoRepositoryStatus
import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RepoViewModel(private val repoRepositoryImpl: RepoRepositoryImpl): ViewModel() {
    val status = MutableLiveData<RepoRepositoryStatus>()
    fun getReposByUsername(username: String){
        viewModelScope.launch {
            when (val response = repoRepositoryImpl.getReposByUsername(username)){
                is RepoRepositoryStatus.Success -> {
                    status.value = RepoRepositoryStatus.Success(response.repoList)
                }
                is RepoRepositoryStatus.NotFound -> {
                    status.value = RepoRepositoryStatus.NotFound
                }
                is RepoRepositoryStatus.Error -> {
                    status.value = RepoRepositoryStatus.Error(response.error)
                }
            }
        }
    }
}