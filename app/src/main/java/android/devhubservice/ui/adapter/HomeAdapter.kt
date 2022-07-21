package android.devhubservice.ui.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.raywenderlich.devhubservice.databinding.ReposItemBinding
import android.service.models.RepoModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(
    val repoList: List<RepoModel>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    class ViewHolder(val binding: ReposItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ReposItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repoList[position]
        with(holder){
            with(repo){
                binding.tvRepoName.text = repo.name
                binding.tvLetter.text = repo.name.get(0).toString()
                binding.ivRepoLink.setOnClickListener {
                    linkRedirectModel(repo.html_url, binding)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    private fun linkRedirectModel(url: String, binding: ReposItemBinding) {
        val intent = Intent(Intent.ACTION_VIEW)
        val bundle = Bundle()
        intent.setData(Uri.parse(url))
        startActivity(binding.root.context, intent, bundle)
    }
}