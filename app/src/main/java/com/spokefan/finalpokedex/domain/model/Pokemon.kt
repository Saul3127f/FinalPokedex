package com.spokefan.finalpokedex.domain.model

data class Pokemon(
    val id: String= "",
    val name: String= "",
    val imageUrl: String = "",
    val type: List<String?> = emptyList()
)
