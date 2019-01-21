package com.uvezem.features.main

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.R
import kotlinx.android.synthetic.main.home_activity.*


class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        navController = Navigation.findNavController(this, R.id.nav_home_fragment)
        homeBottomNavigationView.setOnNavigationItemReselectedListener { bottomNavClick(it) }
    }

    private fun bottomNavClick(item: MenuItem) {
        when (item.itemId) {
            R.id.free_bids_item -> navController.navigate(R.id.freeBidsFragment)
        }
    }

    fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}