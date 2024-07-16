package com.spokefan.finalpokedex.presentation.pokemonDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pokedex.R
import com.spokefan.finalpokedex.ui.theme.interFont


@Composable
fun PokemonDescriptionSection(
    flavorText: String
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 19.dp, vertical = 16.dp)
    ) {
        val description = flavorText.replace("\n", " ")

        Text(
            text = description,
            color = Color(0xFF7E7E7E),
            fontFamily = interFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )

        Icon(
            painter = painterResource(id = R.drawable.down_arrow),
            contentDescription = null,
            tint = Color(0xEEA5A5A5),
            modifier = Modifier
                .size(33.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

private fun addLineBreakToCategory(category: String): String {
    val words = category.split(" ")
    val wordCount = words.size
    return if (wordCount <= 2) {
        category.replaceFirst(" ", "\n")
    } else {
        val index = category.lastIndexOf(" ")
        category.substring(0, index) + "\n" + category.substring(index + 1)
    }
}