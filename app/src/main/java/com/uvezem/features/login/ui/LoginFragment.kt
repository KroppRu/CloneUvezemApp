package com.uvezem.features.login.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.LoginRepository
import com.uvezem.features.login.domain.LoginInteractorImpl
import com.uvezem.features.login.presenter.LoginPresenter
import com.uvezem.features.prefs.Preference
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(), LoginView {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var presenter: LoginPresenter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initDependency()
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    private fun initDependency() {
        val prefs = Preference()
        val loginRepository = LoginRepository(App.apiRetrofit)
        val loginInteractor = LoginInteractorImpl(loginRepository, prefs)
        presenter = LoginPresenter(this, loginInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        presenter.prepareUserData()
        btnLogin.setOnClickListener {
            presenter.onLoginBtnClick(
                login.editText?.text.toString(),
                pass.editText?.text.toString()
            )
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(error: String) {

    }

    override fun next() {
        navController.navigate(R.id.homeActivity)
    }
}