package com.hrithikvish.dictionary.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hrithikvish.dictionary.domain.model.WordItem

@Composable
    fun WordResult(wordItem: WordItem) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 32.dp)
        ) {
            items(wordItem.meanings.size) { index ->
                Meaning(
                    meaning = wordItem.meanings[index],
                    index = index
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }