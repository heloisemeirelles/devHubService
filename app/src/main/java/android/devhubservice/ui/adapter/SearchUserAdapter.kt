package android.devhubservice.ui.adapter

import android.devhubservice.ui.dialog.favorite.AddFavoriteRepoDialog
import android.devhubservice.ui.fragment.HomeFragment
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.UserItemBinding
import android.service.constants.UserSingleton
import android.service.models.UsersSearchResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch


class SearchUserAdapter(
    private val usersSearchResponse: UsersSearchResponse,
    private val favoriteListViewModel: FavoriteListViewModel,
    private val favoriteRepoViewModel: FavoriteRepoViewModel,
    private val usernameWrittenInSearchBar: String
) :
    RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {


    class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = usersSearchResponse.items[position]
        with(holder) {
            with(response) {
                checkIfUserIsFavorite(response.login, binding)
                binding.tvSearchUsername.text = response.login
                Glide.with(binding.root.context).load(response.avatar_url)
                    .into(binding.ciSearchProfilePicture)
                binding.cvSearchUserItem.setOnClickListener {
                    val activity = it.context as AppCompatActivity
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_view_tag, HomeFragment(response.login))
                        .commitNow()
                }
                binding.ivAddFavoriteRepo.setOnClickListener {
                    AddFavoriteRepoDialog(
                        (LayoutInflater.from(binding.root.context)),
                        favoriteListViewModel,
                        favoriteRepoViewModel,
                        response.login,
                        response.avatar_url,
                        usernameWrittenInSearchBar
                    ).chooseDialog()
                }
            }
        }
    }

    private fun checkIfUserIsFavorite(login: String, binding: UserItemBinding) {
        favoriteRepoViewModel.viewModelScope.launch {
            favoriteRepoViewModel.getFavoriteByUser(
                login = UserSingleton.getLogin().toString(),
                repoUsername = login
            )
            if(favoriteRepoViewModel.listData.isNotEmpty()){
                binding.ivAddFavoriteRepo.setImageResource(R.drawable.red_favorite_icon)
            }
        }


    }

    override fun getItemCount(): Int {
        return usersSearchResponse.items.size
    }
}