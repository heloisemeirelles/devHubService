package android.devhubservice.viewModel.user

import android.devhubservice.search.userRepository.UserRepositoryImpl
import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import java.lang.Exception

class UserViewModel(private val userRepositoryImpl: UserRepositoryImpl): ViewModel() {
    val status = MutableLiveData<UserRepositoryStatus>()
    val exists = MutableLiveData<Boolean>()
    fun getUserByUsername(username: String){
        viewModelScope.launch {
            when (val response = userRepositoryImpl.getUserByUserName(username)){
                is UserRepositoryStatus.Success -> {
                    status.value = UserRepositoryStatus.Success(response.userModel)
                }
                is UserRepositoryStatus.NotFound -> {
                    status.value = UserRepositoryStatus.NotFound
                }
                is UserRepositoryStatus.Error -> {
                    status.value = UserRepositoryStatus.Error(response.error)
                }
            }
        }
    }

    fun checkIfUserExists(username: String){

        viewModelScope.launch {
            UserViewModel(userRepositoryImpl).getUserByUsername(username)
            UserViewModel(userRepositoryImpl).status.observeForever(){
                when(it){
                    is UserRepositoryStatus.Success -> {
                        Log.v("mytag","viewmodel true")
                        exists.value = true
                    }
                    else -> {
                        Log.v("mytag","viewmodel false")
                        exists.value = false
                    }
                }
            }
        }
    }
}