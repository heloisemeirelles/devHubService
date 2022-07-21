package android.service.models

data class UsersSearchResponse (
    val total_count: Int,
    val items: List<UserModel>
        )