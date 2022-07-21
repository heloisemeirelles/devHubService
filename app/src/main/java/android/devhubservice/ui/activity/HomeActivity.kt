package android.devhubservice.ui.activity

import android.content.Intent
import android.devhubservice.search.userRepository.UserRepositoryStatus
import android.devhubservice.ui.fragment.FavoriteFragment
import android.devhubservice.ui.fragment.HomeFragment
import android.devhubservice.ui.fragment.SearchFragment
import android.devhubservice.viewModel.logged.LoggedViewModel
import android.devhubservice.viewModel.login.LoginViewModel
import android.devhubservice.viewModel.user.UserViewModel
import android.os.Bundle
import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.UserNotFoundBinding
import android.service.models.LoggedModel
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.app.Dialog
import android.devhubservice.ui.dialog.login.RegisterDialog
import android.devhubservice.ui.fragment.EmptyStateListFragment
import android.devhubservice.viewModel.favorite.list.FavoriteListViewModel
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    private val viewModel: UserViewModel by viewModel()
    private val loggedViewModel: LoggedViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    lateinit var notFoundDialogBinding: UserNotFoundBinding
    private val favoriteListViewModel: FavoriteListViewModel by viewModel()
    private var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        username = intent.getStringExtra("username")
        setDrawerLayout()
        setNavigationViewHeader()
        changeFragment("Home", HomeFragment(username))
        obBackButtonPressed()

    }

    private fun setNavigationViewHeader() {
        navigationView.setNavigationItemSelectedListener(this)
        val header = navigationView.getHeaderView(0)
        val usernameTextView = header.findViewById<TextView>(R.id.tv_menuUsername)
        usernameTextView.text = username
        val imageView = header.findViewById<ImageView>(R.id.ci_menuProfilePicture)
        getNavigationViewHeaderInformation(imageView)
    }

    private fun getNavigationViewHeaderInformation(
        imageView: ImageView
    ) {
        viewModel.getUserByUsername(username.toString())
        viewModel.status.observe(this) { status ->
            when (status) {
                is UserRepositoryStatus.Success -> {
                    Glide.with(this).load(status.userModel.avatar_url).into(imageView)
                }
                is UserRepositoryStatus.NotFound -> {
                }
                is UserRepositoryStatus.Error -> {
                    if (status.error.message == "HTTP 404 ") {
                        notFoundDialogBinding = UserNotFoundBinding.inflate(layoutInflater)
                        val dialog =
                            Dialog(notFoundDialogBinding.root.context, R.style.DialogTheme)
                        dialog.setContentView(notFoundDialogBinding.root)
                        dialog.show()
                        notFoundDialogBinding.btCheckUsername.setOnClickListener {
                            dialog.dismiss()
                            RegisterDialog(
                                layoutInflater,
                                loginViewModel,
                                this,
                                viewModel
                            ).showDialog()
                        }
                    }
                }
            }
        }
    }


    private fun setDrawerLayout() {
        drawerLayout = findViewById(R.id.dl_homeMenu)
        navigationView = findViewById(R.id.navigation)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open,
                R.string.close

            )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun obBackButtonPressed() {
        this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                changeFragment("Home", HomeFragment(username))
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START, false)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START, false)
        if (item.itemId == R.id.homeFragment) {
            changeFragment("Home", HomeFragment(username))
        }
        if (item.itemId == R.id.favoriteFragment) {
            chooseFavoriteFragment()
        }
        if (item.itemId == R.id.searchFragment) {
            changeFragment("Search", SearchFragment())
        }
        if (item.itemId == R.id.bt_signOut) {
            loggedViewModel.deleteUser(LoggedModel(username!!))
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        return true
    }

    private fun chooseFavoriteFragment() {
        favoriteListViewModel.viewModelScope.launch {
            favoriteListViewModel.getAllLists()
            if(favoriteListViewModel.listData.isNotEmpty()){
                changeFragment("Favorites", FavoriteFragment())
            } else {
                changeFragment("Favorites", EmptyStateListFragment())
            }
        }
    }

    private fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun changeFragment(title: String, frag: Fragment) {
        setToolBarTitle(title)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container_view_tag, frag).commit()
    }


}
