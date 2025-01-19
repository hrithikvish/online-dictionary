package com.hrithikvish.dictionary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrithikvish.dictionary.domain.model.Meaning

@Composable
    fun Meaning(
        meaning: Meaning,
        index: Int
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            // 1. noun
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${index + 1}.",
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(
                            top = 2.dp,
                            bottom = 4.dp,
                            start = 12.dp,
                            end = 12.dp
                        )
                )

                Text(
                    text = "${meaning.partOfSpeech}",
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primary.copy(0.4f),
                                    Color.Transparent
                                )
                            )
                        )
                        .padding(
                            top = 2.dp,
                            bottom = 4.dp,
                            start = 12.dp,
                            end = 12.dp
                        )
                )
            }

            // Definition: blah blah blah
            meaning.definition?.definition?.let { definition ->
                if(definition.isNotBlank()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {

                        Text(
                            text = "Definition:",
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    end = 6.dp
                                )
                        )

                        Text(
                            text = definition,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(
                                    start = 6.dp,
                                    end = 12.dp
                                )
                        )
                    }
                }
            }

            // Example: blah blah blah
            meaning.definition?.example?.let { example ->
                if(example.isNotBlank()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {

                        Text(
                            text = "Example:",
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    end = 6.dp
                                )
                        )

                        Text(
                            text = example,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(
                                    start = 6.dp,
                                    end = 12.dp
                                )
                        )
                    }
                }
            }
        }

    }