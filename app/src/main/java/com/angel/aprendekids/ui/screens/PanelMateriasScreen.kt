package com.angel.aprendekids.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.aprendekids.R
import com.angel.aprendekids.domain.model.Estudiante

@Composable
fun PanelMateriasScreen(
    estudiante: Estudiante?,
    onRegresarClick: () -> Unit,
    onMateriaClick: (String) -> Unit,
    onLogrosClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    val nombreEstudiante = estudiante?.nombre ?: "Estudiante"
    val puntosActuales = estudiante?.puntos ?: 0
    val rachaActual = estudiante?.rachaDias ?: 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onRegresarClick,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.btn_regresar),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(R.string.btn_regresar), fontWeight = FontWeight.Bold)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = onLogrosClick,
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(text = stringResource(R.string.btn_logros), fontWeight = FontWeight.Bold)
                }
                OutlinedButton(
                    onClick = onPerfilClick,
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(text = stringResource(R.string.btn_perfil), fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = stringResource(R.string.saludo_inicio, nombreEstudiante),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Avatar: ${estudiante?.avatar ?: "Zorro"} • Grado: ${estudiante?.gradoEscolar ?: "3.°"}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text = stringResource(R.string.resumen_puntos, puntosActuales, rachaActual),
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val matMatematicas = stringResource(R.string.materia_matematicas)
        val matEspanol = stringResource(R.string.materia_espanol)
        val matCiencias = stringResource(R.string.materia_ciencias)
        val matHistoria = stringResource(R.string.materia_historia)
        val matGeografia = stringResource(R.string.materia_geografia)
        val matIngles = stringResource(R.string.materia_ingles)

        BotonMateria(
            nombre = matMatematicas,
            descripcion = stringResource(R.string.desc_matematicas),
            color = MaterialTheme.colorScheme.primary,
            onClick = { onMateriaClick(matMatematicas) }
        )
        BotonMateria(
            nombre = matEspanol,
            descripcion = stringResource(R.string.desc_espanol),
            color = MaterialTheme.colorScheme.error,
            onClick = { onMateriaClick(matEspanol) }
        )
        BotonMateria(
            nombre = matCiencias,
            descripcion = stringResource(R.string.desc_ciencias),
            color = MaterialTheme.colorScheme.tertiary,
            onClick = { onMateriaClick(matCiencias) }
        )
        BotonMateria(
            nombre = matHistoria,
            descripcion = stringResource(R.string.desc_historia),
            color = MaterialTheme.colorScheme.surfaceTint,
            onClick = { onMateriaClick(matHistoria) }
        )
        BotonMateria(
            nombre = matGeografia,
            descripcion = stringResource(R.string.desc_geografia),
            color = MaterialTheme.colorScheme.secondary,
            onClick = { onMateriaClick(matGeografia) }
        )
        BotonMateria(
            nombre = matIngles,
            descripcion = stringResource(R.string.desc_ingles),
            color = MaterialTheme.colorScheme.inversePrimary,
            onClick = { onMateriaClick(matIngles) }
        )
    }
}

@Composable
private fun BotonMateria(nombre: String, descripcion: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
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