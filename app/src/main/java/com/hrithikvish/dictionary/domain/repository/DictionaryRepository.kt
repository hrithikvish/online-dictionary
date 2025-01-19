package com.hrithikvish.dictionary.domain.repository

import com.hrithikvish.dictionary.domain.model.WordItem
import com.hrithikvish.dictionary.util.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun getWordResult(word: String): Flow<Result<WordItem>>

}