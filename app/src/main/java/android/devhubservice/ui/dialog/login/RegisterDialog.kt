package android.devhubservice.ui.dialog.login

import android.app.Activity
import android.app.Dialog
import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.devhubservice.viewModel.login.LoginViewModel
import android.devhubservice.viewModel.user.UserViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.CreateLoginDialogBinding
import android.service.models.LoginModel
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast

class RegisterDialog(
    private val layoutInflater: LayoutInflater,
    private val loginViewModel: LoginViewModel,
    private val currentActivity: Activity,
    private val userViewModel: UserViewModel
) {

    private lateinit var loginDialogBinding: CreateLoginDialogBinding
    fun showDialog() {
        loginDialogBinding = CreateLoginDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(loginDialogBinding.root.context, R.style.DialogTheme)
        dialog.setContentView(loginDialogBinding.root)
        dialog.show()
        loginDialogBinding.btCreateLogin.setOnClickListener {
            createLogin(dialog)
        }
    }

    private fun createLogin(dialog: Dialog) {
        val allFilled = verifyFields()
        if (allFilled) {
            checkIfUserExists(loginDialogBinding.etNewUsername.text.toString(), dialog)
        }
    }

    private fun checkIfUserExists(login: String, dialog: Dialog) {

        userViewModel.getUserByUsername(login)
        userViewModel.status.observeForever {
            when (it) {
                is UserRepositoryStatus.Success -> {
                    addUser(LoginModel(loginDialogBinding.etNewUsername.text.toString(),loginDialogBinding.etNewPassword.text.toString()))
                    dialog.dismiss()
                    SuccessDialog(layoutInflater).showDialog()
                }
                is UserRepositoryStatus.Error -> {
                    if (it.error.message.equals("HTTP 404 ")) {
                        Log.v(
                            "mytag",
                            "igual: ${it.error.message.equals("HTTP 404 ")}, valor: ${it.error.message}"
                        )
                        GitWrongUsername(layoutInflater).showDialog()
                    }
                }
            }
        }

    }

    private fun verifyFields(): Boolean {
        var allFilled = true
        val userFilled =
            loginViewModel.verifyIfFieldIsFilled(this.loginDialogBinding.etNewUsername.text.toString())
        if (!userFilled) {
            Toast.makeText(
                loginDialogBinding.root.context,
                this.currentActivity.getText(R.string.forgotUsername),
                Toast.LENGTH_LONG
            ).show()
            allFilled = false
        } else {
            val passwordFilled =
                loginViewModel.verifyIfFieldIsFilled(this.loginDialogBinding.etNewPassword.text.toString())
            if (!passwordFilled) {
                Toast.makeText(
                    loginDialogBinding.root.context,
                    this.currentActivity.getText(R.string.forgotPassword),
                    Toast.LENGTH_LONG
                ).show()
                allFilled = false
            } else {
                val confirmPasswordFilled =
                    loginViewModel.verifyIfFieldIsFilled(this.loginDialogBinding.etConfirmNewPassword.text.toString())
                if (!confirmPasswordFilled) {
                    Toast.makeText(
                        loginDialogBinding.root.context,
                        this.currentActivity.getText(R.string.forgotConfirmPassword),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    allFilled = false
                } else {
                    val passwordMatches = loginViewModel.verifyIdPasswordsMatch(
                        this.loginDialogBinding.etNewPassword.text.toString(),
                        this.loginDialogBinding.etConfirmNewPassword.text.toString()
                    )
                    if (!passwordMatches) {
                        Toast.makeText(
                            loginDialogBinding.root.context,
                            this.currentActivity.getText(R.string.passwordsDontMatch),
                            Toast.LENGTH_LONG
                        ).show()
                        allFilled = false
                    }
                }
            }
        }
        return allFilled
    }

    private fun addUser(loginModel: LoginModel) {
        loginViewModel.addUser(loginModel)
    }
}