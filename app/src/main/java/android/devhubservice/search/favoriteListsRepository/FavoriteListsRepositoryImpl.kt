package android.devhubservice.search.favoriteListsRepository

import android.service.database.favorites.list.FavoriteListsDao
import android.service.models.FavoriteListsModel

class FavoriteListsRepositoryImpl(private val favoriteListsDao: FavoriteListsDao) {

    suspend fun addList(favoriteListsModel: FavoriteListsModel) =
        favoriteListsDao.addList(favoriteListsModel)

    suspend fun deleteListByName(favoriteListsModel: FavoriteListsModel) =
        favoriteListsDao.deleteListByName(favoriteListsModel)

    suspend fun updateList(favoriteListsModel: FavoriteListsModel) =
        favoriteListsDao.updateList(favoriteListsModel)

    suspend fun getListsByLogin(login: String) = favoriteListsDao.getListsByLogin(login)
    suspend fun getListsByListName(listName: String) = favoriteListsDao.getListsByListName(listName)
    suspend fun getListsByColor(color: String) = favoriteListsDao.getListsByColor(color)
    suspend fun getAllLists() = favoriteListsDao.getAllLists()
}