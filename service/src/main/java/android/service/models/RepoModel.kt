package android.service.models

data class RepoModel (
    val id: Int,
    val name: String,
    val full_name: String,
    val html_url: String,
    val private: Boolean,
    val owner: OwnerModel,
    val url: String
        )