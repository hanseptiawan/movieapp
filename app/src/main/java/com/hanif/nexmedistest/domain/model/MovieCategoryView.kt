package com.hanif.nexmedistest.domain.model

data class MovieCategoryView (
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)