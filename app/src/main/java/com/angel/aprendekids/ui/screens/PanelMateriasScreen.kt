package com.angel.aprendekids.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.aprendekids.R

@Composable
fun PanelMateriasScreen(
    nombreEstudiante: String,
    mensajeError: String?,
    onRegresarClick: () -> Unit,
    onActualizarNombre: (String) -> Unit,
    onEliminarPerfil: () -> Unit
) {
    var editando by remember { mutableStateOf(false) }
    var nuevoNombre by remember { mutableStateOf(nombreEstudiante) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {

        OutlinedButton(
            onClick = onRegresarClick,
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.btn_regresar),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(R.string.btn_regresar),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.saludo_inicio, nombreEstudiante),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = stringResource(R.string.subtitulo_inicio),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = stringResource(R.string.resumen_puntos, 240, 5),
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        if (editando) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = nuevoNombre,
                    onValueChange = { nuevoNombre = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    label = { Text("Nuevo nombre") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    onActualizarNombre(nuevoNombre)
                    if (nuevoNombre.trim().length >= 2) {
                        editando = false
                    }
                }) {
                    Text("Guardar")
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        nuevoNombre = nombreEstudiante
                        editando = true
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Editar nombre")
                }
                OutlinedButton(
                    onClick = onEliminarPerfil,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Borrar perfil")
                }
            }
        }

        if (mensajeError != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BotonMateria(
            nombre = stringResource(R.string.materia_matematicas),
            descripcion = stringResource(R.string.desc_matematicas),
            color = MaterialTheme.colorScheme.primary
        )
        BotonMateria(
            nombre = stringResource(R.string.materia_espanol),
            descripcion = stringResource(R.string.desc_espanol),
            color = MaterialTheme.colorScheme.error
        )
        BotonMateria(
            nombre = stringResource(R.string.materia_ciencias),
            descripcion = stringResource(R.string.desc_ciencias),
            color = MaterialTheme.colorScheme.tertiary
        )
        BotonMateria(
            nombre = stringResource(R.string.materia_historia),
            descripcion = stringResource(R.string.desc_historia),
            color = MaterialTheme.colorScheme.surfaceTint
        )
        BotonMateria(
            nombre = stringResource(R.string.materia_geografia),
            descripcion = stringResource(R.string.desc_geografia),
            color = MaterialTheme.colorScheme.secondary
        )
        BotonMateria(
            nombre = stringResource(R.string.materia_ingles),
            descripcion = stringResource(R.string.desc_ingles),
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}

@Composable
private fun BotonMateria(nombre: String, descripcion: String, color: Color) {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}