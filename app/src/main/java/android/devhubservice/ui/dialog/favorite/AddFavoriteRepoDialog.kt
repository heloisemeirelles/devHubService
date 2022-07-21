package android.devhubservice.ui.dialog.favorite

import android.app.Dialog
import android.devhubservice.ui.fragment.FavoriteFragment
import android.devhubservice.ui.fragment.SearchFragment
import android.devhubservice.utils.FragmentActions
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.AddFavoriteRepoDialogBinding
import android.raywenderlich.devhubservice.databinding.NoListCreatedBinding
import android.service.constants.Strings
import android.service.constants.UserSingleton
import android.service.models.FavoriteRepoModel
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class AddFavoriteRepoDialog(
    private val layoutInflater: LayoutInflater,
    private val favoriteListViewModel: FavoriteListViewModel,
    private val favoriteRepoViewModel: FavoriteRepoViewModel,
    private val userRepo: String,
    private val avatarUrl: String,
    private val usernameWrittenInSearchBar: String
): AdapterView.OnItemSelectedListener {
    private var listName: String = "init"
    private lateinit var binding: AddFavoriteRepoDialogBinding
    private lateinit var noListCreatedBinding: NoListCreatedBinding

    fun chooseDialog(){
        favoriteListViewModel.viewModelScope.launch {
            favoriteListViewModel.getAllLists()
            if(favoriteListViewModel.listData.isEmpty()){
                showNoListCreatedDialog()
            } else {
                showDialog()
            }
        }
    }

    private fun showNoListCreatedDialog() {
        noListCreatedBinding = NoListCreatedBinding.inflate(layoutInflater)
        val dialog = Dialog(noListCreatedBinding.root.context, R.style.DialogTheme)
        dialog.setContentView(noListCreatedBinding.root)
        dialog.show()
        noListCreatedBinding.btCreateList.setOnClickListener {
            dialog.dismiss()
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view_tag, FavoriteFragment())
                .commitNow()
        }
        noListCreatedBinding.btNoThanks.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showDialog(){
        binding = AddFavoriteRepoDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(binding.root.context, R.style.DialogTheme)
        dialog.setContentView(binding.root)
        dialog.show()
        val arrayOfLists: MutableList<String> = ArrayList()
        binding.spFavoriteLists!!.onItemSelectedListener = this
        favoriteListViewModel.viewModelScope.launch {
            favoriteListViewModel.getListsByLogin(UserSingleton.getLogin().toString())
            favoriteListViewModel.listData.forEach {
                arrayOfLists.add(it.listName)
            }
            val arrayAdapter = ArrayAdapter(binding.root.context, R.layout.create_spinner_list, arrayOfLists)
            arrayAdapter.setDropDownViewResource(R.layout.dropdown_spinner_list)
            binding.spFavoriteLists!!.adapter = arrayAdapter
        }
        binding.btAddRepoToList.setOnClickListener {
            addRepoToList()
            Toast.makeText(binding.root.context, Strings.REPO_ADDED_SUCCESSFULLY, Toast.LENGTH_LONG).show()
            dialog.dismiss()
            FragmentActions(it, SearchFragment(usernameWrittenInSearchBar)).redirect()
        }
    }

    private fun addRepoToList() {
        favoriteRepoViewModel.viewModelScope.launch {
            favoriteRepoViewModel.addFavorite(FavoriteRepoModel(UserSingleton.getLogin().toString(), listName, userRepo, avatarUrl))
        }
    }

    override fun onItemSelected(spinner: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listName = spinner?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}