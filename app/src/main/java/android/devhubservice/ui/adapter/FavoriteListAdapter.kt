package android.devhubservice.ui.adapter

import android.devhubservice.ui.dialog.favorite.RemoveListDialog
import android.devhubservice.ui.fragment.FavoriteListDetailFragment
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.ListItemBinding
import android.service.models.FavoriteListsModel
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class FavoriteListAdapter(
    private val listData: List<FavoriteListsModel>,
    private val favoriteViewModel: FavoriteListViewModel
) :  RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listData[position]
        with(holder) {
            with(list){
                setTextColor(this.listColor, binding)
                binding.tvListName.text = this.listName
                binding.ibDeleteList.setOnClickListener {
                    RemoveListDialog((LayoutInflater.from(binding.root.context)), favoriteViewModel, FavoriteListsModel(this.login, this.listName, this.listColor)).showDialog()
                }
                binding.cvListItem.setOnClickListener { view ->
                    val activity = view.context as AppCompatActivity
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_view_tag, FavoriteListDetailFragment(this.listName))
                        .commitNow()
                }
            }
        }

    }

    private fun setTextColor(listColor: String, binding: ListItemBinding) {
        listColor.apply {
            when(this){
                "Orange" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.orange))
                }
                "Green" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                }

                "Grey" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.grey))
                }

                "Blue" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.blue))
                }

                "Black" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.black))
                }

                "Yellow" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.yellow))
                }

                "Purple" -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.purple_200))
                }
                else -> {
                    binding.tvListName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.light_grey))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}