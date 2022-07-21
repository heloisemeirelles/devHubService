package android.service.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_table")
data class LoginModel(
    val login: String,
    val password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
