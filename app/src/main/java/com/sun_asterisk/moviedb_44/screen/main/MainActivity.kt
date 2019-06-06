package com.sun_asterisk.moviedb_44.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun_asterisk.moviedb_44.R
import com.sun_asterisk.moviedb_44.screen.home.HomeFragment
import com.sun_asterisk.moviedb_44.utils.addFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomeFragment(), R.id.container, true)
    }
}
