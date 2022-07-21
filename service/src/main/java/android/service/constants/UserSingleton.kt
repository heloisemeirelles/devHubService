package android.service.constants

object UserSingleton {
    private var username: String? = null

    fun setLogin(login: String?) {
        username = login
    }
    fun getLogin() = username
}