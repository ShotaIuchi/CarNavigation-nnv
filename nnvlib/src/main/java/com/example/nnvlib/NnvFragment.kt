package com.example.nnvlib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe

abstract class NnvFragment : Fragment() {

    private lateinit var viewModel: Lazy<NnvViewModel>

    protected fun setNnvViewModel(viewModel: Lazy<NnvViewModel>) {
        this.viewModel = viewModel
    }

    abstract fun onInitialize()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.value.nnvHandle.observe(viewLifecycleOwner) { it ->
            it.getContentIfNotHandled()?.let {
                onInitialize()
            }
        }
        viewModel.value.nnvInitialize()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}