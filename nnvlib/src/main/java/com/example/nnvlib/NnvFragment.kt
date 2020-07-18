package com.example.nnvlib

import android.os.Bundle
import androidx.fragment.app.Fragment

open class NnvFragment : Fragment() {

    private lateinit var viewModel: Lazy<NnvViewModel>

    fun setNnvViewModel(viewModel: Lazy<NnvViewModel>) {
        this.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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