package com.example.nnvmd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nnvlib.NnvFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.NnvHandle
import com.example.nnvmd.databinding.TopFragmentBinding

class TopFragment : NnvFragment() {

    companion object {
        fun newInstance() = TopFragment()
    }

    private lateinit var binding : TopFragmentBinding
    private var viewModel = activityViewModels<NnvViewModel>()

    init {
        super.setNnvViewModel(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.top_fragment, container, false)
        return binding.root
    }

    override fun onInitialize() {
        Navigation.findNavController(binding.mapContainer).navigate(R.id.action_toMap)
    }

}