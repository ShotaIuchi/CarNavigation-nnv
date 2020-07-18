package com.example.nnvmd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nnvlib.NnvViewModel
import com.example.nnvmd.databinding.MapFragmentBinding

class MapFragment : Fragment() {

    private lateinit var binding: MapFragmentBinding
    private var viewModel = activityViewModels<NnvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.map_fragment, container, false)
        binding.nnvViewModel = viewModel.value
        return binding.root
    }


}