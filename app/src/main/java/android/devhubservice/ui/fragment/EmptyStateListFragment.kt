package android.devhubservice.ui.fragment

import android.devhubservice.ui.dialog.favorite.AddFavoriteListsDialog
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.os.Bundle
import android.raywenderlich.devhubservice.databinding.EmptyStateListFragmentBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmptyStateListFragment: Fragment() {
    private lateinit var emptyStateListFragmentBinding: EmptyStateListFragmentBinding
    private val favoriteViewModel: FavoriteListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        emptyStateListFragmentBinding = EmptyStateListFragmentBinding.inflate(inflater, container, false)
        return emptyStateListFragmentBinding.root
    }

    override fun onStart() {
        super.onStart()
        emptyStateListFragmentBinding.ibAddFavorite.setOnClickListener {
            AddFavoriteListsDialog(layoutInflater, favoriteViewModel).openDialog()
        }
    }
}