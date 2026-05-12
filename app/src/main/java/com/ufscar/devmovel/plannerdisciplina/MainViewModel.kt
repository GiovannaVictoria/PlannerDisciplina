package com.ufscar.devmovel.plannerdisciplina

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufscar.devmovel.plannerdisciplina.repository.Disciplina

class MainViewModel : ViewModel() {

    var disciplinaSendoEditada by mutableStateOf<Disciplina?>(null)
    var estadoTemporarioDisciplina by mutableStateOf(EstadoAtualizacaoCardDialog())
    val listaDisciplinas = mutableStateListOf<Disciplina>()

    fun adicionarDisciplina() {
        listaDisciplinas.add(Disciplina())
    }

    fun removerDisciplina(disciplina: Disciplina) {
        listaDisciplinas.remove(disciplina)
    }

    fun abrirAtualizarCamposDialog(disciplina: Disciplina) {
        disciplinaSendoEditada = disciplina
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina = disciplina.copy()
            )
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "disciplina: $disciplina")
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "estadoTemporarioDisciplina: $estadoTemporarioDisciplina")
    }

    fun fecharAtualizarCamposDialog() {
        disciplinaSendoEditada = null
    }

    fun alterarEstadoTemporarioNome(nome: String) {
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina =
                    estadoTemporarioDisciplina.disciplina.copy(
                        nome = nome
                    )
            )
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "estadoTemporarioDisciplina: $estadoTemporarioDisciplina")
    }

    fun alterarEstadoTemporarioProfessor(professor: String) {
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina =
                    estadoTemporarioDisciplina.disciplina.copy(
                        professor = professor
                    )
            )
    }

    fun alterarEstadoTemporarioSala(sala: String) {
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina =
                    estadoTemporarioDisciplina.disciplina.copy(
                        sala = sala
                    )
            )
    }

    fun alterarEstadoTemporarioHorario(horario: String) {
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina =
                    estadoTemporarioDisciplina.disciplina.copy(
                        horario = horario
                    )
            )
    }

    fun alterarEstadoTemporarioCampo(nome: String, valor: String) {
        val novoCampo = CampoDisciplina(nome, valor)
        val novaLista = estadoTemporarioDisciplina.disciplina.campos.toMutableList()
        novaLista.add(novoCampo)
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina =
                    estadoTemporarioDisciplina.disciplina.copy(
                        campos = novaLista
                    )
            )
    }

    fun salvarAlteracoes() {
        val indiceDisciplinaAntiga = listaDisciplinas.indexOf(disciplinaSendoEditada)
        disciplinaSendoEditada =
            disciplinaSendoEditada?.copy(
                nome = estadoTemporarioDisciplina.disciplina.nome,
                professor = estadoTemporarioDisciplina.disciplina.professor,
                sala = estadoTemporarioDisciplina.disciplina.sala,
                horario = estadoTemporarioDisciplina.disciplina.horario,
                campos = estadoTemporarioDisciplina.disciplina.campos
            )
        listaDisciplinas[indiceDisciplinaAntiga] = disciplinaSendoEditada!!
        fecharAtualizarCamposDialog()
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "listaDisciplinas: $listaDisciplinas")
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "indiceDisciplinaAntiga: $indiceDisciplinaAntiga")
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "disciplinaSendoEditada: $disciplinaSendoEditada")
        Log.i("MainViewModel - abrirAtualizarCamposDialog", "listaDisciplinas: $listaDisciplinas")
    }

}