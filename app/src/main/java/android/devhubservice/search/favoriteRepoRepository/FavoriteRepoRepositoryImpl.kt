package android.devhubservice.search.favoriteRepoRepository

import android.service.database.favorites.repo.FavoriteDao
import android.service.models.FavoriteListsModel
import android.service.models.FavoriteRepoModel

class FavoriteRepoRepositoryImpl(private val favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favoriteRepoModel: FavoriteRepoModel) =
        favoriteDao.addFavorite(favoriteRepoModel)

    suspend fun deleteFavorite(favoriteRepoModel: FavoriteRepoModel) =
        favoriteDao.deleteFavorite(favoriteRepoModel)

    suspend fun getAllByList(login: String, listName: String) = favoriteDao.getAllByList(login, listName)

    suspend fun getFavoriteByUser(login: String, repoUsername: String) = favoriteDao.getFavoriteByUser(login, repoUsername)

    suspend fun getAll() = favoriteDao.getAll()

}