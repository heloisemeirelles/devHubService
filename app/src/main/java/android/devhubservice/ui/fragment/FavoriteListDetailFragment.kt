package android.devhubservice.ui.fragment

import android.devhubservice.ui.adapter.FavoriteListDetailsAdapter
import android.devhubservice.utils.FragmentActions
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.devhubservice.viewModel.user.UserViewModel
import android.os.Bundle
import android.raywenderlich.devhubservice.databinding.EmptyStateFavoriteReposBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.raywenderlich.devhubservice.databinding.FavoriteListDetailFragmentBinding
import android.service.constants.UserSingleton
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteListDetailFragment(val listName: String) : Fragment() {
    private lateinit var binding: FavoriteListDetailFragmentBinding
    private val favoriteRepoViewModel: FavoriteRepoViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var favoriteListDetailsAdapter: FavoriteListDetailsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FavoriteListDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        favoriteRepoViewModel.viewModelScope.launch {
            favoriteRepoViewModel.getAllByList(UserSingleton.getLogin().toString(), listName)
            if(favoriteRepoViewModel.listData.isEmpty()){
                FragmentActions(binding.root, EmptyStateFavoriteReposFragment()).redirect()
            } else {
                setAdapter()
            }
        }
    }

    private fun setAdapter() {
        favoriteListDetailsAdapter = FavoriteListDetailsAdapter(favoriteRepoViewModel.listData, favoriteRepoViewModel)
        binding.rvFavoriteList.adapter = favoriteListDetailsAdapter
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFavoriteList.layoutManager = linearLayoutManager
    }
}