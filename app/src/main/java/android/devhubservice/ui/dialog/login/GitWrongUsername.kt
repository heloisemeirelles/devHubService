package android.devhubservice.ui.dialog.login

import android.app.Dialog
import android.content.Intent
import android.devhubservice.ui.activity.MainActivity
import android.os.Bundle
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.GithubUserIsWrongBinding
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity


class GitWrongUsername(private val layoutInflater: LayoutInflater) {
    private lateinit var binding: GithubUserIsWrongBinding

    fun showDialog(){
        binding = GithubUserIsWrongBinding.inflate(layoutInflater)
        val dialog = Dialog(binding.root.context, R.style.DialogTheme)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.ibBackButton.setOnClickListener {
            val intent = Intent(binding.root.context, MainActivity::class.java)
            val bundle = Bundle()
            startActivity(binding.root.context, intent, bundle)
        }
    }
}