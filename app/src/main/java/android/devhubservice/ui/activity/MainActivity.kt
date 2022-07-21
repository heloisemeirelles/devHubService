package android.devhubservice.ui.activity

import android.content.Intent
import android.devhubservice.ui.dialog.login.RegisterDialog
import android.devhubservice.viewModel.logged.LoggedViewModel
import android.devhubservice.viewModel.login.LoginViewModel
import android.devhubservice.viewModel.user.UserViewModel
import android.os.Bundle
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.ActivityMainBinding
import android.service.constants.UserSingleton
import android.service.models.LoggedModel
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val loginViewModel: LoginViewModel by viewModel()
    private val loggedViewModel: LoggedViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfUserIsLoggedIn()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btConfirm.setOnClickListener {
            if (fieldsAreFilled()) userRegistered(binding.etUsername.text.toString())
        }

        binding.tvHasNoRegister.setOnClickListener {
            RegisterDialog(layoutInflater, loginViewModel, this, userViewModel).showDialog()
        }

    }

    private fun checkIfUserIsLoggedIn() {
        loggedViewModel.viewModelScope.launch {
            loggedViewModel.getUserByLogin()
            loggedViewModel.loginData.let {
                when (it.lastIndex < 0) {
                    false -> {
                        UserSingleton.setLogin(it[0].login)
                        val bundle = Bundle()
                        val intent = Intent(binding.root.context, HomeActivity::class.java)
                        intent.putExtra("username", it[0].login)
                        ContextCompat.startActivity(binding.root.context, intent, bundle)

                    }
                }
            }
        }
    }

    private fun fieldsAreFilled(): Boolean {
        if (binding.etUsername.text.isEmpty()) {
            Toast.makeText(this, getText(R.string.forgotUsername), Toast.LENGTH_LONG).show()
            return false
        } else if (binding.etPassword.text.isEmpty()) {
            Toast.makeText(this, getText(R.string.forgotPassword), Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }


    private fun userRegistered(username: String) {
        lifecycleScope.launch {
            loginViewModel.getUserByLogin(username)
            loginViewModel.loginData.let {
                when (it.lastIndex < 0) {
                    true -> {
                        binding.etUsername.setBackgroundColor(resources.getColor(R.color.red))
                        binding.etUsername.hint = getText(R.string.wrongUsername)
                        Toast.makeText(
                            applicationContext,
                            getText(R.string.unregisteredUser),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    false -> {
                        binding.etUsername.setBackgroundColor(resources.getColor(R.color.white))
                        if (binding.cbStayConnected.isChecked) {
                            saveUser(
                                binding.etUsername.text.toString(),
                                binding.etPassword.text.toString()
                            )
                        }
                        if (verifyIfPasswordIsCorrect(it[0].password)) {
                            UserSingleton.setLogin(it[0].login)
                            val bundle = Bundle()
                            val intent = Intent(binding.root.context, HomeActivity::class.java)
                            intent.putExtra("username", binding.etUsername.text.toString())
                            ContextCompat.startActivity(binding.root.context, intent, bundle)
                        }
                    }
                }
            }
        }
    }

    private fun saveUser(username: String, password: String) {
        loggedViewModel.addUser(LoggedModel(username))
    }

    private fun verifyIfPasswordIsCorrect(password: String): Boolean {
        return if (password != binding.etPassword.text.toString()) {
            Toast.makeText(
                applicationContext,
                getText(R.string.wrongPassword),
                Toast.LENGTH_LONG
            ).show()
            binding.etPassword.setBackgroundColor(resources.getColor(R.color.red))
            false
        } else {
            binding.etPassword.setBackgroundColor(resources.getColor(R.color.white))
            true
        }

    }

}