package com.hrithikvish.dictionary.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrithikvish.dictionary.domain.repository.DictionaryRepository
import com.hrithikvish.dictionary.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val _dictionaryState = MutableStateFlow(DictionaryState())
    val dictionaryState = _dictionaryState.asStateFlow()

    val searchQueryForXml = MutableLiveData("word")

    init {
        _dictionaryState.update {
            it.copy(searchWord = "Word")
        }
        loadWordResult()
    }

    private var searchJob: Job? = null

    fun onEvent(dictionaryUiEvent: DictionaryUiEvents) {
        when (dictionaryUiEvent) {
            DictionaryUiEvents.OnSearchClick -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    loadWordResult()
                }
            }
            is DictionaryUiEvents.OnSearchWordChange -> {
                _dictionaryState.update {
                    it.copy(searchWord = dictionaryUiEvent.newWord.lowercase())
                }
            }
        }
    }

    private fun loadWordResult() {
        viewModelScope.launch {
            searchQueryForXml.value?.lowercase()?.let { searchQuery ->
                dictionaryRepository.getWordResult(
                    //dictionaryState.value.searchWord
                    searchQuery
                ).collect { result ->
                    when (result) {
                        is Result.Error -> Unit
                        is Result.Loading -> {
                            _dictionaryState.update {
                                it.copy(isLoading = result.isLoading  )
                            }
                        }

                        is Result.Success -> {
                            _dictionaryState.update {
                                it.copy(wordItem = result.data)
                            }
                        }
                    }
                }
            }
        }
    }

}