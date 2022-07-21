package android.service.models

import androidx.room.Entity

@Entity
data class UserModel (
    val login: String,
    val avatar_url: String,
    val bio: String,
    val name: String,
    val url: String
        )