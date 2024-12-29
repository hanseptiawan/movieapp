package com.hanif.nexmedistest.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hanif.nexmedistest.domain.model.MovieCategoryView

class ReusableComponents {

    @Composable
    fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp),
            singleLine = true
        )
    }

    @Composable
    fun FilterChips(categories: List<MovieCategoryView>) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                AssistChip(
                    onClick = { /* Handle filter click */ },
                    label = { Text(category.name) }
                )
            }
        }
    }

}