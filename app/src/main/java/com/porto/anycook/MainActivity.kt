package com.porto.anycook

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.porto.anycook.ui.main.MainViewModel
import com.porto.core.domain.user.models.InitialPage
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector,
    IDefaultFragmentInteractionListener {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val mainViewModel by viewModels<MainViewModel>() { viewModelFactory }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel.onEventReceived(MainViewModel.Event.OnLoadPage)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.app_navigation)
        navController = navHostFragment.navController

        mainViewModel.state.observe(this, Observer {
            it.getContentIfNotHandled().let {state ->
                when(state){
                    is MainViewModel.State.ShowStartPage -> {
                        if(state.page == InitialPage.Authentication)
                            graph.startDestination = R.id.loginFragment
                        else graph.startDestination = R.id.mainFragment

                        navHostFragment.navController.graph = graph
                    }
                }
            }
        })
    }

    override fun navigateTo(destination: NavDirections) {
        navController.navigate(destination)
    }
}

interface IDefaultFragmentInteractionListener {
    fun navigateTo(destination: NavDirections)
}