package android.service.models

import androidx.room.Entity

@Entity(tableName = "favoriteList_table", primaryKeys = ["listName", "listColor" ])
data class FavoriteListsModel(
    val login: String,
    val listName: String,
    val listColor: String
)

