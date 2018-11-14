package com.uvezem.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.uvezem.R
import androidx.navigation.NavController
import androidx.navigation.Navigation


class LoginActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }


}
