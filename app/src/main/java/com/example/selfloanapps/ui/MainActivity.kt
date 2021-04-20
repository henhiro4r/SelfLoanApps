package com.example.selfloanapps.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.selfloanapps.R
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.ui.login.LoginViewModel
import com.example.selfloanapps.ui.viewModelFactory.LoginViewModelFactory
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel
import com.example.selfloanapps.ui.viewModelFactory.SelfLoanViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var viewModel: SelfLoanViewModel
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MainRepository()
        val viewModelFactory = SelfLoanViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SelfLoanViewModel::class.java)

        val loginFactory = LoginViewModelFactory(application, repository)
        loginViewModel = ViewModelProvider(this, loginFactory).get(LoginViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {
                navView.visibility = View.INVISIBLE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_active_loan, R.id.nav_history, R.id.nav_account)
        )

        setSupportActionBar(mainToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}