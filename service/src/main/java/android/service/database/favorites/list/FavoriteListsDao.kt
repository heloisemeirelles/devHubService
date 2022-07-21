package android.service.database.favorites.list

import android.service.models.FavoriteListsModel
import androidx.room.*

@Dao
interface FavoriteListsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addList(favoriteListsModel: FavoriteListsModel)

    @Delete
    suspend fun deleteListByName(favoriteListsModel: FavoriteListsModel)

    @Update
    suspend fun updateList(favoriteListsModel: FavoriteListsModel)

    @Query("SELECT * FROM favoriteList_table WHERE login = :login")
    suspend fun getListsByLogin(login: String): List<FavoriteListsModel>

    @Query("SELECT * FROM favoriteList_table WHERE listName LIKE '%' || :listName || '%'")
    suspend fun getListsByListName(listName: String): List<FavoriteListsModel>

    @Query("SELECT * FROM favoriteList_table WHERE listColor = :color")
    suspend fun getListsByColor(color: String): List<FavoriteListsModel>

    @Query("SELECT * FROM favoriteList_table")
    suspend fun getAllLists(): List<FavoriteListsModel>

}