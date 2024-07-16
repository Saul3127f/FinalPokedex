package com.spokefan.finalpokedex.util

import androidx.compose.ui.graphics.Color
import com.pokedex.R
import com.spokefan.finalpokedex.ui.theme.Bug
import com.spokefan.finalpokedex.ui.theme.Dark
import com.spokefan.finalpokedex.ui.theme.Dragon
import com.spokefan.finalpokedex.ui.theme.Electric
import com.spokefan.finalpokedex.ui.theme.Fairy
import com.spokefan.finalpokedex.ui.theme.Fighting
import com.spokefan.finalpokedex.ui.theme.Fire
import com.spokefan.finalpokedex.ui.theme.Flying
import com.spokefan.finalpokedex.ui.theme.Ghost
import com.spokefan.finalpokedex.ui.theme.Grass
import com.spokefan.finalpokedex.ui.theme.Ground
import com.spokefan.finalpokedex.ui.theme.Ice
import com.spokefan.finalpokedex.ui.theme.Normal
import com.spokefan.finalpokedex.ui.theme.Poison
import com.spokefan.finalpokedex.ui.theme.Psychic
import com.spokefan.finalpokedex.ui.theme.Rock
import com.spokefan.finalpokedex.ui.theme.Steel
import com.spokefan.finalpokedex.ui.theme.Water
import java.util.Locale

fun parseTypeToDrawable(type: String): Int {
    return when(type.lowercase(Locale.ROOT)){
        "normal" -> R.drawable.normal
        "fire" -> R.drawable.fire
        "grass" -> R.drawable.grass
        "water" -> R.drawable.water
        "rock" -> R.drawable.rock
        "fairy" -> R.drawable.fairy
        "ghost" -> R.drawable.ghost
        "fighting" -> R.drawable.fighting
        "ice" -> R.drawable.ice
        "bug" -> R.drawable.bug
        "dark" -> R.drawable.dark
        "dragon" -> R.drawable.dragon
        "ground" -> R.drawable.ground
        "electric" -> R.drawable.electric
        "flying" -> R.drawable.flying
        "poison" -> R.drawable.poison
        "steel" -> R.drawable.steel
        "psychic" -> R.drawable.psychic
        else -> R.drawable.normal
    }
}
fun parseTypeToColor(type: String): Color {
    return when(type.lowercase(Locale.ROOT)) {
        "normal" -> Normal
        "fire" -> Fire
        "water" -> Water
        "electric" -> Electric
        "grass" -> Grass
        "ice" -> Ice
        "fighting" -> Fighting
        "poison" -> Poison
        "ground" -> Ground
        "flying" -> Flying
        "psychic" -> Psychic
        "bug" -> Bug
        "rock" -> Rock
        "ghost" -> Ghost
        "dragon" -> Dragon
        "dark" -> Dark
        "steel" -> Steel
        "fairy" -> Fairy
        else -> Normal
    }
}