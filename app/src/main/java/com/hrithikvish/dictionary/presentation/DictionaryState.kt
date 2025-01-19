package com.hrithikvish.dictionary.presentation

import com.hrithikvish.dictionary.domain.model.WordItem

data class DictionaryState(
    val isLoading: Boolean = false,
    val searchWord: String = "",
    val wordItem: WordItem? = null
)