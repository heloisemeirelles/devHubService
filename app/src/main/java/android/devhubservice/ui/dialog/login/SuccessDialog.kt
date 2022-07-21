package android.devhubservice.ui.dialog.login

import android.app.Dialog
import android.content.Intent
import android.devhubservice.ui.activity.MainActivity
import android.os.Bundle
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.SuccessCreateLoginDialogBinding
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity

class SuccessDialog(private val layoutInflater: LayoutInflater) {

    private lateinit var binding: SuccessCreateLoginDialogBinding
    fun showDialog() {
        binding = SuccessCreateLoginDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(binding.root.context, R.style.DialogTheme)
        dialog.setContentView(binding.root)
        dialog.show()
        binding.btSignIn.setOnClickListener {
            dialog.dismiss()
            val bundle = Bundle()
            val intent = Intent(binding.root.context, MainActivity::class.java)
            startActivity(binding.root.context, intent, bundle)
        }
    }
}