package com.sun.quickquotes.ui.main

import android.content.SharedPreferences
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseActivity
import com.sun.quickquotes.databinding.ActivityMainBinding
import com.sun.quickquotes.utils.SharedPreferencesExtension.get
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_FIRST_TIME_OPEN
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId get() = R.layout.activity_main
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }
    private val sharedPreferences: SharedPreferences by inject()

    override fun initViews() {
        if (sharedPreferences[KEY_FIRST_TIME_OPEN, true]) {
            navController.navigate(R.id.getStartedFragment)
            viewBinding.bottomNavigation.apply {
                visibility = View.GONE
            }
        } else {
            setUpBottomNavigation()
        }
    }

    fun setUpBottomNavigation() {

    }

}
