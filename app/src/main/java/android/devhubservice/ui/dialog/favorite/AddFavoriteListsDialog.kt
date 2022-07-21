package android.devhubservice.ui.dialog.favorite

import android.app.Dialog
import android.devhubservice.ui.fragment.FavoriteFragment
import android.devhubservice.ui.fragment.HomeFragment
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.AddFavoriteDialogBinding
import android.service.constants.UserSingleton
import android.service.models.FavoriteListsModel
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AddFavoriteListsDialog(
    val layoutInflater: LayoutInflater,
    val favoriteViewModel: FavoriteListViewModel
) :
    AdapterView.OnItemSelectedListener {
    private var itemColor: String = "init"
    private val dialogBinding = AddFavoriteDialogBinding.inflate(layoutInflater)
    fun openDialog() {

        val dialog = Dialog(dialogBinding.root.context, R.style.DialogTheme)
        dialog.setContentView(dialogBinding.root)
        dialog.show()

        ArrayAdapter.createFromResource(
            dialogBinding.root.context,
            R.array.colorsArray,
            R.layout.create_spinner_list
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.dropdown_spinner_list)
            dialogBinding.spColor.adapter = adapter
            dialogBinding.spColor.onItemSelectedListener = this
        }
        dialogBinding.btCreateList.setOnClickListener {
            if (verifyFields()) {
                addList()
                dialog.dismiss()
                val activity = it.context as AppCompatActivity
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view_tag, FavoriteFragment())
                    .commitNow()
            }
        }
    }

    private fun addList() {
        val favoriteListsModel = FavoriteListsModel(
            UserSingleton.getLogin().toString(),
            dialogBinding.etListName.text.toString(),
            itemColor
        )
        favoriteViewModel.addList(favoriteListsModel)
    }

    private fun verifyFields(): Boolean {
        dialogBinding.apply {
            if (this.etListName.text.isEmpty()) {
                Toast.makeText(
                    dialogBinding.root.context,
                    "O nome da lista está vazio!",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
        }
        return true
    }

    override fun onItemSelected(spinner: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        itemColor = spinner?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}
