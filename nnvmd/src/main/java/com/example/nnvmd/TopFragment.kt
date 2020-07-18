package com.example.nnvmd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nnvlib.NnvFragment
import androidx.fragment.app.activityViewModels
import com.example.nnvlib.NnvViewModel

class TopFragment : NnvFragment() {

    companion object {
        fun newInstance() = TopFragment()
    }

    private var viewModel = activityViewModels<NnvViewModel>()

    init {
        super.setNnvViewModel(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

}