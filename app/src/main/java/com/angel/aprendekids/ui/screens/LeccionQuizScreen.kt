package com.angel.aprendekids.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.aprendekids.R

private data class Pregunta(
    val enunciado: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int
)

@Composable
fun LeccionQuizScreen(
    materia: String,
    onCompletarLeccion: (puntosGanados: Int) -> Unit,
    onVolverClick: () -> Unit
) {
    val preguntas = remember(materia) { obtenerPreguntasPorMateria(materia) }
    var indiceActual by remember { mutableIntStateOf(0) }
    var opcionSeleccionada by remember { mutableStateOf<Int?>(null) }
    var aciertos by remember { mutableIntStateOf(0) }
    var terminado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        OutlinedButton(
            onClick = onVolverClick,
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.btn_regresar),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = stringResource(R.string.btn_regresar), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = materia,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!terminado) {
            val pregunta = preguntas[indiceActual]

            Text(
                text = "Pregunta ${indiceActual + 1} de ${preguntas.size}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = pregunta.enunciado,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    pregunta.opciones.forEachIndexed { index, textoOpcion ->
                        val esSeleccionada = opcionSeleccionada == index
                        OutlinedButton(
                            onClick = { opcionSeleccionada = index },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (esSeleccionada) {
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                } else {
                                    MaterialTheme.colorScheme.surface
                                }
                            )
                        ) {
                            Text(
                                text = textoOpcion,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = if (esSeleccionada) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (opcionSeleccionada == pregunta.respuestaCorrecta) {
                                aciertos++
                            }
                            if (indiceActual < preguntas.lastIndex) {
                                indiceActual++
                                opcionSeleccionada = null
                            } else {
                                terminado = true
                            }
                        },
                        enabled = opcionSeleccionada != null,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (indiceActual < preguntas.lastIndex) {
                                stringResource(R.string.btn_siguiente_pregunta)
                            } else {
                                "Finalizar lección"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        } else {
            val puntosGanados = aciertos * 20
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "¡Lección completada!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Respuestas correctas: $aciertos de ${preguntas.size}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "+$puntosGanados puntos ganados",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { onCompletarLeccion(puntosGanados) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.btn_volver_materias),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

private fun obtenerPreguntasPorMateria(materia: String): List<Pregunta> {
    return when {
        materia.contains("Matemáticas", ignoreCase = true) -> listOf(
            Pregunta("¿Cuánto es 7 x 6?", listOf("36", "42", "48"), 1),
            Pregunta("Si tienes 45 manzanas y regalas 18, ¿cuántas quedan?", listOf("27", "33", "23"), 0),
            Pregunta("¿Cuántos lados tiene un hexágono?", listOf("5", "6", "8"), 1)
        )
        materia.contains("Español", ignoreCase = true) -> listOf(
            Pregunta("¿Cuál palabra es un sustantivo propio?", listOf("perro", "México", "mesa"), 1),
            Pregunta("¿Cuál es el antónimo de 'rápido'?", listOf("veloz", "lento", "alto"), 1),
            Pregunta("¿Qué signo se usa al final de una pregunta?", listOf("!", "?", "."), 1)
        )
        materia.contains("Ciencias", ignoreCase = true) -> listOf(
            Pregunta("¿Qué necesitan las plantas para realizar la fotosíntesis?", listOf("Luz solar", "Oscuridad", "Hielo"), 0),
            Pregunta("¿Cuál es el planeta más cercano al Sol?", listOf("Marte", "Mercurio", "Júpiter"), 1),
            Pregunta("¿En qué estado está el agua en un cubo de hielo?", listOf("Líquido", "Gaseoso", "Sólido"), 2)
        )
        materia.contains("Historia", ignoreCase = true) -> listOf(
            Pregunta("¿En qué año inició la Independencia de México?", listOf("1810", "1910", "1521"), 0),
            Pregunta("¿Qué cultura construyó Chichén Itzá?", listOf("Olmeca", "Maya", "Tolteca"), 1),
            Pregunta("¿Quién es conocido como el Benemérito de las Américas?", listOf("Miguel Hidalgo", "Benito Juárez", "Emiliano Zapata"), 1)
        )
        materia.contains("Geografía", ignoreCase = true) -> listOf(
            Pregunta("¿Cuál es el océano más grande del mundo?", listOf("Atlántico", "Índico", "Pacífico"), 2),
            Pregunta("¿Cuál es la capital de México?", listOf("Guadalajara", "Ciudad de México", "Monterrey"), 1),
            Pregunta("¿En qué continente se encuentra Egipto?", listOf("África", "Asia", "Europa"), 0)
        )
        else -> listOf(
            Pregunta("¿Cómo se dice 'Manzana' en inglés?", listOf("Apple", "Orange", "Grape"), 0),
            Pregunta("¿Qué color es 'Blue'?", listOf("Rojo", "Azul", "Verde"), 1),
            Pregunta("¿Cómo se dice 'Gracias' en inglés?", listOf("Please", "Hello", "Thank you"), 2)
        )
    }
}