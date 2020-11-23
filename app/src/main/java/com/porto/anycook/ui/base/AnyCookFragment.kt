package com.porto.anycook.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.porto.anycook.IDefaultFragmentInteractionListener
import com.porto.anycook.injection.Injectable
import javax.inject.Inject

abstract class AnyCookFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    abstract val layoutResourceId : Int

    lateinit var fragmentInteractionListener: IDefaultFragmentInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is IDefaultFragmentInteractionListener)
            fragmentInteractionListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    protected fun showToast(message: String?) {
        val showError = message ?: "Something went wrong"
        Toast.makeText(context, showError, Toast.LENGTH_SHORT).show()
    }

    protected fun alertFieldEmpty() {
        Toast.makeText(context, "Please fill required fields", Toast.LENGTH_SHORT).show()
    }


}

