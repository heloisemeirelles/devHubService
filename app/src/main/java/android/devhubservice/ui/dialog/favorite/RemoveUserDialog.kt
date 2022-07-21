package android.devhubservice.ui.dialog.favorite

import android.app.Dialog
import android.devhubservice.ui.fragment.FavoriteListDetailFragment
import android.devhubservice.utils.FragmentActions
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.RemoveUserDialogBinding
import android.service.constants.Strings
import android.service.models.FavoriteRepoModel
import android.view.LayoutInflater
import android.widget.Toast

class RemoveUserDialog(
    private val favoriteRepoViewModel: FavoriteRepoViewModel,
    private val layoutInflater: LayoutInflater
) {

    private lateinit var binding: RemoveUserDialogBinding
    fun showDialog(response: FavoriteRepoModel) {
        binding = RemoveUserDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(binding.root.context, R.style.DialogTheme)
        dialog.setContentView(binding.root)
        dialog.show()

        binding.btDeleteUser.setOnClickListener {

            favoriteRepoViewModel.deleteFavorite(response)
            Toast.makeText(
                binding.root.context,
                Strings.REPO_DELETED_SUCCESSFULLY,
                Toast.LENGTH_LONG
            ).show()
            dialog.dismiss()
            FragmentActions(it, FavoriteListDetailFragment(response.listName)).redirect()

        }
    }
}