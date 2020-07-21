package com.example.nnv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.example.nnv.databinding.SearchFragmentBinding
import com.example.nnvlib.NnvViewModel

class SearchFragment : Fragment() {

    private lateinit var binding : SearchFragmentBinding
    private val nnvViewModel: NnvViewModel by activityViewModels()
    private val viewModel: SearchViewModel by navGraphViewModels(R.id.nested_search)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.svm = viewModel
        viewModel.word.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            viewModel.searchSuggestion(nnvViewModel, it)
        })
        viewModel.searchSuggestion.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.getContentIfNotHandled()?.let { si ->
                si.forEach { su ->
                    Log.d("TTTTTTTTT", su.suggestionInfo.suggestion)
                }
            }
        })
        return binding.root
    }


}