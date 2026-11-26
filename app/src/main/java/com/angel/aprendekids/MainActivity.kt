package com.angel.aprendekids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import com.angel.aprendekids.di.AppContainer
import com.angel.aprendekids.ui.screens.*
import com.angel.aprendekids.ui.theme.AprendeKidsTheme
import com.angel.aprendekids.ui.viewmodel.AprendeKidsViewModel

private enum class PantallaApp {
    ACCESO,
    MATERIAS,
    LECCION,
    LOGROS,
    PERFIL
}

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

                var pantallaActual by remember { mutableStateOf(PantallaApp.ACCESO) }
                var materiaSeleccionada by remember { mutableStateOf("Matemáticas") }

                when (pantallaActual) {
                    PantallaApp.ACCESO -> {
                        AccesoUsuarioScreen(
                            mensajeError = mensajeError,
                            onEntrarClick = { nombreIngresado ->
                                viewModel.registrarIngreso(nombreIngresado) {
                                    pantallaActual = PantallaApp.MATERIAS
                                }
                            }
                        )
                    }

                    PantallaApp.MATERIAS -> {
                        PanelMateriasScreen(
                            estudiante = estudianteGuardado,
                            onRegresarClick = {
                                viewModel.limpiarError()
                                pantallaActual = PantallaApp.ACCESO
                            },
                            onMateriaClick = { materia ->
                                materiaSeleccionada = materia
                                pantallaActual = PantallaApp.LECCION
                            },
                            onLogrosClick = {
                                pantallaActual = PantallaApp.LOGROS
                            },
                            onPerfilClick = {
                                viewModel.limpiarError()
                                pantallaActual = PantallaApp.PERFIL
                            }
                        )
                    }

                    PantallaApp.LECCION -> {
                        LeccionQuizScreen(
                            materia = materiaSeleccionada,
                            onCompletarLeccion = { puntosGanados ->
                                viewModel.sumarPuntos(puntosGanados)
                                pantallaActual = PantallaApp.MATERIAS
                            },
                            onVolverClick = {
                                pantallaActual = PantallaApp.MATERIAS
                            }
                        )
                    }

                    PantallaApp.LOGROS -> {
                        LogrosProgresoScreen(
                            estudiante = estudianteGuardado,
                            onVolverClick = {
                                pantallaActual = PantallaApp.MATERIAS
                            }
                        )
                    }

                    PantallaApp.PERFIL -> {
                        PerfilTutorScreen(
                            estudiante = estudianteGuardado,
                            mensajeError = mensajeError,
                            onActualizarPerfil = { nombre, grado, avatar ->
                                viewModel.actualizarPerfilCompleto(nombre, grado, avatar)
                            },
                            onEliminarPerfil = {
                                viewModel.eliminarPerfilActual {
                                    pantallaActual = PantallaApp.ACCESO
                                }
                            },
                            onVolverClick = {
                                viewModel.limpiarError()
                                pantallaActual = PantallaApp.MATERIAS
                            }
                        )
                    }
                }
            }
        }
    }
}