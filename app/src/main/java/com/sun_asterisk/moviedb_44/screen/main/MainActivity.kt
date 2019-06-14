package com.sun_asterisk.moviedb_44.screen.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.screen.favorite.FavoriteFragment
import com.sun_asterisk.moviedb_44.screen.home.HomeFragment
import com.sun_asterisk.moviedb_44.utils.BottomNavigationBehavior
import com.sun_asterisk.moviedb_44.utils.addFragment
import com.sun_asterisk.moviedb_44.utils.hideFragment
import com.sun_asterisk.moviedb_44.utils.showFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var favoriteFragment: FavoriteFragment
    private lateinit var homeFragment: HomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        addFragment(FavoriteFragment.getInstance(), R.id.container)
        addFragment(HomeFragment.getInstance(), R.id.container)
        navigation.setOnNavigationItemSelectedListener(this)
        val layoutParams = navigation.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigationHome -> {
                hideFragment(favoriteFragment)
                showFragment(homeFragment)
                return true
            }

            R.id.navigationFavorite ->  {
                hideFragment(homeFragment)
                showFragment(favoriteFragment)
                return true
            }
        }
        return false
    }
}
