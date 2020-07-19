package com.example.nnvmd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nnvlib.NnvViewModel
import com.example.nnvmd.databinding.RootFragmentBinding
import com.example.nnvmd.databinding.RouteFragmentBinding

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