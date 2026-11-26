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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.aprendekids.R
import com.angel.aprendekids.domain.model.Estudiante

@Composable
fun LogrosProgresoScreen(
    estudiante: Estudiante?,
    onVolverClick: () -> Unit
) {
    val puntos = estudiante?.puntos ?: 0
    val racha = estudiante?.rachaDias ?: 1
    val nivel = (puntos / 60) + 1
    val progresoNivel = (puntos % 60) / 60f

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
            text = stringResource(R.string.titulo_logros),
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
                    text = "Nivel $nivel • ${estudiante?.nombre ?: "Estudiante"}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Puntos totales: $puntos pts  |  Racha activa: $racha días")
                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = { progresoNivel },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Insignias del Estudiante",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        TarjetaInsignia(
            titulo = "Primer Paso",
            descripcion = "Crear tu perfil en AprendeKids",
            desbloqueada = estudiante != null
        )
        TarjetaInsignia(
            titulo = "Aprendiz Curioso",
            descripcion = "Alcanzar 40 puntos en lecciones",
            desbloqueada = puntos >= 40
        )
        TarjetaInsignia(
            titulo = "Mente Brillante",
            descripcion = "Alcanzar 100 puntos acumulados",
            desbloqueada = puntos >= 100
        )
        TarjetaInsignia(
            titulo = "Maestro Primaria",
            descripcion = "Superar los 200 puntos",
            desbloqueada = puntos >= 200
        )
    }
}

@Composable
private fun TarjetaInsignia(titulo: String, descripcion: String, desbloqueada: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (desbloqueada) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
            }
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = if (desbloqueada) "★ $titulo (Desbloqueada)" else "🔒 $titulo (Bloqueada)",
                fontWeight = FontWeight.Bold,
                color = if (desbloqueada) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = descripcion, style = MaterialTheme.typography.bodySmall)
        }
    }
}