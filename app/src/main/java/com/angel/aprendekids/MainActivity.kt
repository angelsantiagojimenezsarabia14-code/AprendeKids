package com.angel.aprendekids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.angel.aprendekids.di.AppContainer
import com.angel.aprendekids.ui.screens.AccesoUsuarioScreen
import com.angel.aprendekids.ui.screens.PanelMateriasScreen
import com.angel.aprendekids.ui.theme.AprendeKidsTheme
import com.angel.aprendekids.ui.viewmodel.AprendeKidsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = AppContainer(applicationContext)
        val viewModel = ViewModelProvider(
            this,
            AprendeKidsViewModel.Factory(appContainer.repository)
        )[AprendeKidsViewModel::class.java]

        setContent {
            AprendeKidsTheme {
                val estudianteGuardado by viewModel.estudianteActual.collectAsState()
                val mensajeError by viewModel.mensajeError.collectAsState()
                var sesionIniciada by remember { mutableStateOf(false) }

                if (!sesionIniciada) {
                    AccesoUsuarioScreen(
                        mensajeError = mensajeError,
                        onEntrarClick = { nombreIngresado ->
                            viewModel.registrarIngreso(nombreIngresado) {
                                sesionIniciada = true
                            }
                        }
                    )
                } else {
                    PanelMateriasScreen(
                        nombreEstudiante = estudianteGuardado?.nombre ?: "Estudiante",
                        mensajeError = mensajeError,
                        onRegresarClick = {
                            viewModel.limpiarError()
                            sesionIniciada = false
                        },
                        onActualizarNombre = { nuevoNombre ->
                            viewModel.actualizarNombreEstudiante(nuevoNombre)
                        },
                        onEliminarPerfil = {
                            viewModel.eliminarPerfilActual {
                                sesionIniciada = false
                            }
                        }
                    )
                }
            }
        }
    }
}