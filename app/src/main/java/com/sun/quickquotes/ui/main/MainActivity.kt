package com.sun.quickquotes.ui.main

import androidx.navigation.fragment.NavHostFragment
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseActivity
import com.sun.quickquotes.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId get() = R.layout.activity_main
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }

    override fun initViews() {

    }

    private fun firstTimeOpen() {

    }

}
