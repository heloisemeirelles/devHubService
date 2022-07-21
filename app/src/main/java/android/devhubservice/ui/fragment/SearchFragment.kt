package android.devhubservice.ui.fragment

import android.devhubservice.search.searchUserRepositoryStatus.SearchUserRepositoryStatus
import android.devhubservice.ui.adapter.SearchUserAdapter
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.devhubservice.viewModel.user.SearchViewModel
import android.os.Bundle
import android.raywenderlich.devhubservice.databinding.SearchFragmentBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment(val usernameFromDialog: String? = null) : Fragment() {

    lateinit var binding: SearchFragmentBinding
    private lateinit var searchUserAdapter: SearchUserAdapter
    private val favoriteListViewModel: FavoriteListViewModel by viewModel()
    private val favoriteRepoViewModel: FavoriteRepoViewModel by viewModel()
    val viewModel: SearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(!usernameFromDialog.isNullOrBlank()) callAdapter(usernameFromDialog)
        binding.btSearch.setOnClickListener { callAdapter(binding.etSearchUsername.text.toString()) }
    }

    private fun callAdapter(usernameFromDialog: String) {
        viewModel.getUserByQuery(usernameFromDialog)
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is SearchUserRepositoryStatus.Success -> {
                    searchUserAdapter = SearchUserAdapter(status.searchResponse, favoriteListViewModel, favoriteRepoViewModel, binding.etSearchUsername.text.toString())
                    binding.rvProfiles.adapter = searchUserAdapter
                    val linearLayoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvProfiles.layoutManager = linearLayoutManager
                }
            }
        }
    }
}