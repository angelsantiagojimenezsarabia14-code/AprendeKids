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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.aprendekids.R
import com.angel.aprendekids.domain.model.Estudiante

@Composable
fun PerfilTutorScreen(
    estudiante: Estudiante?,
    mensajeError: String?,
    onActualizarPerfil: (nombre: String, grado: String, avatar: String) -> Unit,
    onEliminarPerfil: () -> Unit,
    onVolverClick: () -> Unit
) {
    var nombre by remember(estudiante) { mutableStateOf(estudiante?.nombre ?: "") }
    var grado by remember(estudiante) { mutableStateOf(estudiante?.gradoEscolar ?: "3.°") }
    var avatarSeleccionado by remember(estudiante) { mutableStateOf(estudiante?.avatar ?: "Zorro") }
    var guardadoOk by remember { mutableStateOf(false) }

    val avatares = listOf("Zorro", "Conejo", "Oso", "Gato")
    val grados = listOf("1.°", "2.°", "3.°", "4.°", "5.°", "6.°")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
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
            text = stringResource(R.string.titulo_perfil),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "ID Estudiante: #${estudiante?.idEstudiante ?: 0}  •  ID Tutor vinculado: #${estudiante?.idTutor ?: 0}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        guardadoOk = false
                    },
                    label = { Text("Nombre del estudiante") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (mensajeError != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = mensajeError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(text = "Grado escolar", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    grados.forEach { itemGrado ->
                        FilterChip(
                            selected = grado == itemGrado,
                            onClick = {
                                grado = itemGrado
                                guardadoOk = false
                            },
                            label = { Text(itemGrado) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(text = "Avatar", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    avatares.forEach { avatar ->
                        FilterChip(
                            selected = avatarSeleccionado == avatar,
                            onClick = {
                                avatarSeleccionado = avatar
                                guardadoOk = false
                            },
                            label = { Text(avatar) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        onActualizarPerfil(nombre, grado, avatarSeleccionado)
                        if (nombre.trim().length >= 2) {
                            guardadoOk = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Guardar cambios (UPDATE)", fontWeight = FontWeight.Bold)
                }

                if (guardadoOk) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Cambios guardados en la base de datos.",
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onEliminarPerfil,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Eliminar perfil y salir (DELETE)", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}