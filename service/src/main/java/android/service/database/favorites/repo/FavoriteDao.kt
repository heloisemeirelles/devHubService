package android.service.database.favorites.repo

import android.service.models.FavoriteListsModel
import android.service.models.FavoriteRepoModel
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteRepoModel: FavoriteRepoModel)

    @Delete
    suspend fun deleteFavorite(favoriteRepoModel: FavoriteRepoModel)

    @Query("SELECT * FROM favoriteRepo_table WHERE login = :login AND listName = :listName")
    suspend fun getAllByList(login: String, listName: String): List<FavoriteRepoModel>

    @Query("SELECT * FROM favoriteRepo_table")
    suspend fun getAll(): List<FavoriteRepoModel>

    @Query("SELECT * FROM favoriteRepo_table WHERE login = :login AND repoUsername = :repoUsername")
    suspend fun getFavoriteByUser(login: String, repoUsername: String): List<FavoriteRepoModel>
}