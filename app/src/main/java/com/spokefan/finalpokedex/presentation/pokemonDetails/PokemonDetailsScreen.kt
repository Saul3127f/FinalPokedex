package com.spokefan.finalpokedex.presentation.pokemonDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spokefan.finalpokedex.domain.model.PokemonDetails
import com.pokedex.R
import com.spokefan.finalpokedex.ui.theme.interFont
import com.spokefan.finalpokedex.ui.theme.sfProFont
import com.spokefan.finalpokedex.util.parseTypeToColor


@Composable
fun PokemonDetailsContent(
    pokemonDetails: PokemonDetails
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PokemonTypesSection(types = pokemonDetails.type)
        PokemonDetailsRow(pokemonDetails = pokemonDetails)
        PokemonDescriptionSection(flavorText = pokemonDetails.flavorText)
    }
}
@Composable
fun PokemonTypesSection(
    types: List<String?>
) {
    Row(
        modifier = Modifier
            .padding(start = if (types.size == 1) 20.dp else 10.dp)
            .padding(top = 30.dp)
            .fillMaxWidth()
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 11.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        for (type in types) {
            Box(
                modifier = Modifier
                    .width(96.dp)
                    .height(30.dp)
                    .background(color = parseTypeToColor(type!!), shape = CircleShape)
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = type.uppercase(),
                    color = Color.White,
                    fontFamily = sfProFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun PokemonDetailsRow(
    pokemonDetails: PokemonDetails
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = addLineBreakToCategory(pokemonDetails.category),
            color = Color.Black,
            fontFamily = interFont,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier
            .width(2.5.dp)
            .height(65.dp)
            .background(Color(0x4FD3D3D3))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "${pokemonDetails.weight} lbs.",
                color = Color.Black,
                fontFamily = interFont,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )

            Text(
                text = "Weight",
                color = Color(0xFF7E7E7E),
                fontFamily = interFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }

        Box(modifier = Modifier
            .width(2.5.dp)
            .height(65.dp)
            .background(Color(0x4FD3D3D3))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "${pokemonDetails.height}'",
                color = Color.Black,
                fontFamily = interFont,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp
            )

            Text(
                text = "Height",
                color = Color(0xFF7E7E7E),
                fontFamily = interFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}
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