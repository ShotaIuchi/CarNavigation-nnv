package com.example.nnvmd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nnvlib.NnvViewModel

class RouteFragment : Fragment() {

    private var viewModel = activityViewModels<NnvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.value.longTapPoint.observe(viewLifecycleOwner, Observer { p ->
            p.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.toPont)
            }
        })
        return inflater.inflate(R.layout.route_fragment, container, false)
    }


}