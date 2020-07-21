package com.example.nnv.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nnvlib.NnvViewModel
import com.example.nnv.R
import com.example.nnv.databinding.MapFragmentBinding

class MapFragment : Fragment() {

    private lateinit var binding: MapFragmentBinding
    private val viewModel by activityViewModels<NnvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.map_fragment, container, false)
        binding.nnvViewModel = viewModel
        return binding.root
    }


}