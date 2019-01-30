package com.uvezem.features.login.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uvezem.App
import com.uvezem.R
import com.uvezem.data.LoginRepository
import com.uvezem.domain.LoginInteractorImpl
import com.uvezem.domain.UserInteractorImpl
import com.uvezem.features.login.presenter.LoginPresenter
import com.uvezem.data.prefs.Preference
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
        val loginInteractor = LoginInteractorImpl(loginRepository)
        val userInteractor = UserInteractorImpl(prefs)
        presenter = LoginPresenter(this, loginInteractor, userInteractor)
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
        pgView.visibility = View.VISIBLE
        pg.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgView.visibility = View.GONE
        pg.visibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, LENGTH_LONG).show()
    }

    override fun next() {
        navController.navigate(R.id.homeActivity)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}