package android.devhubservice.ui.adapter

import android.devhubservice.ui.dialog.favorite.RemoveUserDialog
import android.devhubservice.viewModel.favorite.repo.FavoriteRepoViewModel
import android.raywenderlich.devhubservice.databinding.FavoriteUserRepoItemBinding
import android.service.models.FavoriteRepoModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavoriteListDetailsAdapter(
    private val listOfRepos: List<FavoriteRepoModel>,
    private val favoriteRepoViewModel: FavoriteRepoViewModel
) : RecyclerView.Adapter<FavoriteListDetailsAdapter.ViewHolder>() {

    class ViewHolder(val binding: FavoriteUserRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            FavoriteUserRepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = listOfRepos[position]
        with(holder){
            with(response){
                Glide.with(binding.root).load(response.avatarUrl).into(binding.ciRepoProfilePicture)
                binding.tvUserRepo.text = response.repoUsername
                binding.ibDeleteRepo.setOnClickListener {
                    RemoveUserDialog(favoriteRepoViewModel, LayoutInflater.from(binding.root.context)).showDialog(response)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfRepos.size
    }
}