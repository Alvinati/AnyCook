package com.porto.anycook.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.porto.anycook.IDefaultFragmentInteractionListener
import com.porto.anycook.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var listener: IDefaultFragmentInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is IDefaultFragmentInteractionListener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        val navHost = childFragmentManager.findFragmentById(R.id.main_host_fragment)
                as NavHostFragment
        btnv_main.setupWithNavController(navHost.navController)
    }


}
