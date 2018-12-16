package com.uvezem.features.login.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.uvezem.R
import com.uvezem.features.login.presenter.LoginPresenter
import com.uvezem.model.UserApp
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(), LoginView {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = LoginPresenter()
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        presenter.prepareUserData()
        btnLogin.setOnClickListener { navController.navigate(R.id.homeActivity) }
    }

    override fun setUserData(userApp: UserApp) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {

    }
}