package com.angel.aprendekids.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.angel.aprendekids.domain.model.Estudiante
import com.angel.aprendekids.domain.model.Tutor
import com.angel.aprendekids.domain.repository.AprendeKidsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AprendeKidsViewModel(
    private val repository: AprendeKidsRepository
) : ViewModel() {

    val estudianteActual: StateFlow<Estudiante?> = repository.observarEstudianteActual()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _mensajeError = MutableStateFlow<String?>(null)
    val mensajeError: StateFlow<String?> = _mensajeError.asStateFlow()

    fun limpiarError() {
        _mensajeError.value = null
    }


    fun registrarIngreso(nombreIngresado: String, onGuardado: () -> Unit) {
        val nombreLimpio = nombreIngresado.trim()
        if (nombreLimpio.isEmpty()) {
            _mensajeError.value = "Por favor, escribe tu nombre para entrar."
            return
        }
        if (nombreLimpio.length < 2) {
            _mensajeError.value = "El nombre debe tener al menos 2 letras."
            return
        }

        _mensajeError.value = null
        viewModelScope.launch {
            val idTutor = repository.guardarTutor(
                Tutor(retoMatematico = "8 x 3 = 24")
            )

            repository.guardarEstudiante(
                Estudiante(
                    idTutor = idTutor,
                    nombre = nombreLimpio,
                    apellido = "",
                    edad = "8 años",
                    gradoEscolar = "3.°",
                    avatar = "Zorro"
                )
            )
            onGuardado()
        }
    }

    fun actualizarNombreEstudiante(nuevoNombre: String) {
        val nombreLimpio = nuevoNombre.trim()
        val actual = estudianteActual.value ?: return
        if (nombreLimpio.length < 2) {
            _mensajeError.value = "Escribe un nombre válido (mínimo 2 letras) para actualizar."
            return
        }
        _mensajeError.value = null
        viewModelScope.launch {
            repository.actualizarEstudiante(actual.copy(nombre = nombreLimpio))
        }
    }


    fun eliminarPerfilActual(onEliminado: () -> Unit) {
        val actual = estudianteActual.value ?: return
        viewModelScope.launch {
            repository.eliminarEstudiante(actual)
            onEliminado()
        }
    }

    class Factory(
        private val repository: AprendeKidsRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AprendeKidsViewModel(repository) as T
        }
    }
}