package android.devhubservice.ui.fragment

import android.content.Intent
import android.devhubservice.search.repoRepository.RepoRepositoryStatus
import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.devhubservice.ui.adapter.HomeAdapter
import android.devhubservice.viewModel.repo.RepoViewModel
import android.devhubservice.viewModel.user.UserViewModel
import android.net.Uri
import android.os.Bundle
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.HomeFragmentBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment(val username: String?) : Fragment() {
    private val userViewModel: UserViewModel by viewModel()
    private val repoViewModel: RepoViewModel by viewModel()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: HomeFragmentBinding
    private var results = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        userViewModel.getUserByUsername(username.toString())
        userViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                is UserRepositoryStatus.Success -> {
                    Glide.with(binding.root).load(it.userModel.avatar_url)
                        .into(binding.ivProfilePicture)
                    binding.tvName.text = it.userModel.name
                    binding.tvUsername.text = it.userModel.login
                    if (it.userModel.bio != null){
                        binding.tvBio.text = it.userModel.bio.replace("\n", "")
                    } else {
                        binding.tvAbout.visibility = View.GONE
                    }
                    setAdapter()
                }
            }
        }
    }

    private fun setAdapter() {
        repoViewModel.getReposByUsername(username.toString())
        repoViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                is RepoRepositoryStatus.Success -> {
                    results = setNumberOfRepos(it.repoList.size)
                    binding.tvResults.text = results
                    binding.ibLink.setOnClickListener { listener ->
                        linkRedirectModel(it.repoList.get(0).owner.html_url)
                    }
                    homeAdapter = HomeAdapter(it.repoList)
                    binding.rvReposList.adapter = homeAdapter
                    val linearLayoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvReposList.layoutManager = linearLayoutManager
                }

            }
        }
    }

    private fun setNumberOfRepos(size: Int): String {
        return if (size <= 1) {
            "$size ${getText(R.string.repository)}"
        } else {
            "$size ${getText(R.string.repositories)}"
        }
    }

    private fun linkRedirectModel(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}