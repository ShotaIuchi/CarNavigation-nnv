package com.example.nnvmd

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nnvlib.NnvViewModel
import com.example.nnvmd.databinding.PointDetailFragmentBinding

class PointDetailFragment : Fragment() {

    private lateinit var binding : PointDetailFragmentBinding
    private var viewModel = activityViewModels<NnvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.point_detail_fragment, container, false)
        binding.point = viewModel.value.longTapPoint.value?.peekContent()
        binding.frame.setOnClickListener {
            // NOP
        }
        binding.toRoute.setOnClickListener {
            findNavController().navigate(R.id.toRoute)
        }
        return binding.root
    }

}