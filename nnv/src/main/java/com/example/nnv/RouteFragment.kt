package com.example.nnv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nnvlib.NnvViewModel
import com.example.nnv.databinding.RootFragmentBinding
import com.example.nnv.databinding.RouteFragmentBinding

class RouteFragment : Fragment() {

    private lateinit var binding : RouteFragmentBinding
    private var viewModel = activityViewModels<NnvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.route_fragment, container, false)
        return binding.root
    }

}