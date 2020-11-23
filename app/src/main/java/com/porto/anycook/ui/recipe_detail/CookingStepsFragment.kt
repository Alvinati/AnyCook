package com.porto.anycook.ui.recipe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.porto.anycook.R

class CookingStepsFragment : Fragment() {


    companion object {
        fun newInstance() = IngredientsFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipe_detail_steps_fragment, container, false)
    }

}