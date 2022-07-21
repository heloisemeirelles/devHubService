package android.service.apiService.api

import android.service.constants.Constants.BASE_URL
import android.service.models.RepoModel
import android.service.models.UserModel
import android.service.models.UsersSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DevHubService {
    @GET("${BASE_URL}users/{username}")
    suspend fun getUserByUsername(@Path(value = "username") username: String): UserModel

    @GET("${BASE_URL}users/{username}/repos")
    suspend fun getReposByUsername(@Path(value = "username") username: String): List<RepoModel>

    @GET("${BASE_URL}search/users?q=")
    suspend fun getUserByQuery(
        @Query("q") username: String,
        @Query("per_page") page_max: Int = 20
    ): UsersSearchResponse
}