package com.hrithikvish.dictionary.data.respository

import com.hrithikvish.dictionary.data.api.DictionaryApi
import com.hrithikvish.dictionary.data.mapper.toWordItem
import com.hrithikvish.dictionary.domain.model.WordItem
import com.hrithikvish.dictionary.domain.repository.DictionaryRepository
import com.hrithikvish.dictionary.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
) : DictionaryRepository {

    override suspend fun getWordResult(word: String): Flow<Result<WordItem>> = flow {
        emit(Result.Loading(true))

        val remoteWordResultDto = try {
            dictionaryApi.getWordResult(word)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error("Can't get result"))
            emit(Result.Loading(false))
            return@flow
        }

        remoteWordResultDto?.let { wordResultDto ->
            wordResultDto[0]?.let { wordItemDto ->
                emit(Result.Success(wordItemDto.toWordItem()))
                emit(Result.Loading(false))
                return@flow
            }
        }

        emit(Result.Error("Can't get result"))
        emit(Result.Loading(false))
    }

}