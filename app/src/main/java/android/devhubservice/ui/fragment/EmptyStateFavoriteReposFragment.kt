package android.devhubservice.ui.fragment

import android.devhubservice.utils.FragmentActions
import android.os.Bundle
import android.raywenderlich.devhubservice.databinding.EmptyStateFavoriteReposBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class EmptyStateFavoriteReposFragment: Fragment() {
    private lateinit var emptyStateFavoriteReposBinding: EmptyStateFavoriteReposBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        emptyStateFavoriteReposBinding = EmptyStateFavoriteReposBinding.inflate(inflater, container, false)
        return emptyStateFavoriteReposBinding.root
    }

    override fun onStart() {
        super.onStart()
        emptyStateFavoriteReposBinding.ibSearchFragment.setOnClickListener {
            FragmentActions(it, SearchFragment()).redirect()
        }
    }
}