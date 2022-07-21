package android.devhubservice.ui.dialog.favorite

import android.app.Dialog
import android.devhubservice.ui.fragment.FavoriteFragment
import android.devhubservice.utils.FragmentActions
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.ConfirmDeleteListDialogBinding
import android.service.models.FavoriteListsModel
import android.view.LayoutInflater


class RemoveListDialog(
    val layoutInflater: LayoutInflater,
    val favoriteViewModel: FavoriteListViewModel,
    val favoriteListsModel: FavoriteListsModel
) {
    lateinit var confirmDeleteListDialogBinding: ConfirmDeleteListDialogBinding
    fun showDialog(){
        confirmDeleteListDialogBinding = ConfirmDeleteListDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(confirmDeleteListDialogBinding.root.context, R.style.DialogTheme)
        dialog.setContentView(confirmDeleteListDialogBinding.root)
        dialog.show()

        confirmDeleteListDialogBinding.btDeleteList.setOnClickListener {
            deleteList(dialog)
            FragmentActions(it, FavoriteFragment()).redirect()
        }
        confirmDeleteListDialogBinding.btDontDeleteList.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun deleteList(dialog: Dialog) {
        favoriteViewModel.deleteListByName(favoriteListsModel)
        dialog.dismiss()
    }
}