package android.devhubservice.ui.adapter

import android.devhubservice.ui.dialog.favorite.RemoveListDialog
import android.devhubservice.ui.fragment.FavoriteListDetailFragment
import android.devhubservice.utils.ColorDataSource
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.ListItemBinding
import android.service.models.FavoriteListsModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
                ColorDataSource(this.listColor, binding).setColor()
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

    override fun getItemCount(): Int {
        return listData.size
    }
}