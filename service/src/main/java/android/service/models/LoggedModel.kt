package android.service.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logged_table")
data class LoggedModel (
    @PrimaryKey
    val login: String
        )