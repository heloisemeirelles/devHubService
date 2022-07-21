package android.devhubservice.ui.fragment

import android.content.Intent
import android.devhubservice.ui.adapter.FavoriteListAdapter
import android.devhubservice.ui.dialog.favorite.AddFavoriteListsDialog
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.os.Bundle
import android.raywenderlich.devhubservice.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.raywenderlich.devhubservice.databinding.FavoriteFragmentBinding
import android.service.constants.UserSingleton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteBinding: FavoriteFragmentBinding
    private val favoriteViewModel: FavoriteListViewModel by viewModel()
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoriteBinding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return favoriteBinding.root
    }

    override fun onStart() {
        super.onStart()
        setAdapter()
        favoriteBinding.ibAddFavorite.setOnClickListener {
            AddFavoriteListsDialog(layoutInflater, favoriteViewModel).openDialog()
        }

    }

    private fun setAdapter() {
        favoriteViewModel.viewModelScope.launch {
            favoriteViewModel.getListsByLogin(UserSingleton.getLogin().toString())
            if (favoriteViewModel.listData.isEmpty()){
                callEmptyStateFragment()
            } else {
                favoriteListAdapter = FavoriteListAdapter(favoriteViewModel.listData, favoriteViewModel)
                favoriteBinding.rvFavoriteList.adapter = favoriteListAdapter
                val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                favoriteBinding.rvFavoriteList.layoutManager = linearLayoutManager
            }
        }
    }

    private fun callEmptyStateFragment() {
        val activity = favoriteBinding.root.context as AppCompatActivity
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_tag, EmptyStateListFragment())
            .commitNow()
    }
}