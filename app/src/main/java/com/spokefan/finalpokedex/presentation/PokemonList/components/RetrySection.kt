package com.spokefan.finalpokedex.presentation.PokemonList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spokefan.finalpokedex.ui.theme.sfProFont
import com.spokefan.finalpokedex.ui.theme.PokedexAppTheme

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error,
            fontFamily = sfProFont,
            fontWeight = FontWeight.Normal,
            color = Color.Red,
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(17.dp))
        Button(
            onClick = { onRetry() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5269AD)
            )
        ) {
            Text(
                text = "Retry",
                fontFamily = sfProFont,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RetrySectionPreview() {
    PokedexAppTheme {
        RetrySection(error = "Retry section preview") {
        }
    }
}