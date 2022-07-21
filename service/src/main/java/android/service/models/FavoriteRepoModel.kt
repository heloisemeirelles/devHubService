package android.service.models

import androidx.room.Entity

@Entity(tableName = "favoriteRepo_table", primaryKeys = ["login", "repoUsername", "listName"])
data class FavoriteRepoModel (
    val login: String,
    val listName: String,
    val repoUsername: String,
    val avatarUrl: String
        )
