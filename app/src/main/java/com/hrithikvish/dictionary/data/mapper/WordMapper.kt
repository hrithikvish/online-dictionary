package com.hrithikvish.dictionary.data.mapper

import com.hrithikvish.dictionary.data.dto.DefinitionDto
import com.hrithikvish.dictionary.data.dto.MeaningDto
import com.hrithikvish.dictionary.data.dto.WordItemDto
import com.hrithikvish.dictionary.domain.model.Definition
import com.hrithikvish.dictionary.domain.model.Meaning
import com.hrithikvish.dictionary.domain.model.WordItem

fun WordItemDto.toWordItem() = WordItem(
    word = word ?: "",
    meanings = meanings?.map { it.toMeaning() } ?: emptyList(),
    phonetic = phonetic ?: ""
)

fun MeaningDto.toMeaning() = Meaning(
    definition = definitions?.get(0)?.toDefinition(),
    partOfSpeech = partOfSpeech ?: ""
)

fun DefinitionDto?.toDefinition() = Definition(
    definition = this?.definition ?: "",
    example = this?.example ?: ""
)