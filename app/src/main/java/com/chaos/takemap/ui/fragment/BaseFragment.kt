package com.chaos.takemap.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chaos.takemap.model.BaseGeoJson

abstract class BaseFragment:Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflate(inflater,container,savedInstanceState)
    }

    abstract fun layoutInflate(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?):View

    abstract fun setStyle(mapStyle:Int)

}