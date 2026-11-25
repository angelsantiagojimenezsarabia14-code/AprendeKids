package com.angel.aprendekids.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColoresAprendeKids = lightColorScheme(
    primary = Azul,
    onPrimary = BlancoPuro,
    secondary = Morado,
    onSecondary = BlancoPuro,
    tertiary = Verde,
    onTertiary = BlancoPuro,
    error = Coral,
    onError = BlancoPuro,
    inversePrimary = Amarillo,
    surfaceTint = Naranja,
    background = BlancoHumo,
    onBackground = GrisOscuro,
    surface = BlancoPuro,
    onSurface = GrisOscuro
)

@Composable
fun AprendeKidsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColoresAprendeKids,
        typography = Typography,
        content = content
    )
}