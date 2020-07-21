package com.example.nnv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.SuggestionInfo
import com.example.nnvlib.repository.SearchRepository
import com.example.nnvlib.util.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val word = MutableLiveData<String>()

    private val _searchSuggestion = MutableLiveData<Event<List<SuggestionInfo>>>()
    val searchSuggestion : LiveData<Event<List<SuggestionInfo>>>
        get() = _searchSuggestion

    fun updateSearchSuggestion(suggestions: List<SuggestionInfo>) {
        _searchSuggestion.postValue(Event(suggestions))
    }


    private var searchRepository : SearchRepository? = null

    fun searchSuggestion(nnvViewModel: NnvViewModel, word: String) {
        GlobalScope.launch {
            nnvViewModel.nnvHandle.value?.peekContent()?.let {
                searchRepository?.let { sr ->
                    sr.close()
                    searchRepository = null
                }
                searchRepository = SearchRepository(nnvViewModel, it.handle).apply { searchSuggestion(word) }
            }
        }
    }

}