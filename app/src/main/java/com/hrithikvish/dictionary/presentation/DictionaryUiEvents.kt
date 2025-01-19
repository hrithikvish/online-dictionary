package com.hrithikvish.dictionary.presentation

sealed class DictionaryUiEvents {
    data class OnSearchWordChange(val newWord: String): DictionaryUiEvents()
    object OnSearchClick: DictionaryUiEvents()
}