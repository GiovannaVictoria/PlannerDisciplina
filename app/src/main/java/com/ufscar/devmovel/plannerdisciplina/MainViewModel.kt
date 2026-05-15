package com.ufscar.devmovel.plannerdisciplina

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufscar.devmovel.plannerdisciplina.repository.Disciplina

class MainViewModel : ViewModel() {

    var disciplinaSendoEditada by mutableStateOf<Disciplina?>(null)
    var estadoTemporarioDisciplina by mutableStateOf(EstadoAtualizacaoDisciplina())
    val listaDisciplinas = mutableStateListOf<Disciplina>()
    var adicaoCampoDialogAberto by mutableStateOf(false)
    var campoTemporario by mutableStateOf<CampoDisciplina>(CampoDisciplina())

    fun adicionarDisciplina() {
        val novoId = if (listaDisciplinas.isEmpty()) 0 else listaDisciplinas.maxOf { it.id } + 1
        listaDisciplinas.add(Disciplina(id = novoId))
    }

    fun removerDisciplina(disciplina: Disciplina) {
        listaDisciplinas.remove(disciplina)
    }

    fun setarDisciplinaSendoEditada(disciplina: Disciplina) {
        disciplinaSendoEditada = disciplina
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina = disciplina.copy()
            )
    }

    fun resetarDisciplinaSendoEditada() {
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

    fun alterarEstadoTemporarioCampoValor(nome: String, valor: String) {
        val novaLista = estadoTemporarioDisciplina.disciplina.campos.map { campo ->
            if (campo.nome == nome) campo.copy(valor = valor)
            else campo
        }

        estadoTemporarioDisciplina = estadoTemporarioDisciplina.copy(
            disciplina = estadoTemporarioDisciplina.disciplina.copy(
                campos = novaLista
            )
        )
    }

    fun alterarCampoTemporarioNome(nome: String) {
        campoTemporario = campoTemporario.copy(nome = nome)
    }

    fun alterarCampoTemporarioValor(valor: String) {
        campoTemporario = campoTemporario.copy(valor = valor)
    }

    fun salvarAlteracoesCampo() {
        val novoCampo = CampoDisciplina(campoTemporario.nome, campoTemporario.valor)
        val novaLista = estadoTemporarioDisciplina.disciplina.campos.toMutableList()
        novaLista.add(novoCampo)
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina =
                    estadoTemporarioDisciplina.disciplina.copy(
                        campos = novaLista
                    )
            )
        fecharAdicaoCampoDialog()
    }

    fun salvarAlteracoesDisciplina() {
        val disciplina = disciplinaSendoEditada ?: return
        val indice = listaDisciplinas.indexOfFirst { it.id == disciplina.id }

        if (indice != -1) {
            listaDisciplinas[indice] = estadoTemporarioDisciplina.disciplina
            resetarDisciplinaSendoEditada()
        }
    }

    fun abrirAdicaoCampoDialog() {
        campoTemporario = CampoDisciplina()
        adicaoCampoDialogAberto = true
    }

    fun fecharAdicaoCampoDialog() {
        adicaoCampoDialogAberto = false
    }

}