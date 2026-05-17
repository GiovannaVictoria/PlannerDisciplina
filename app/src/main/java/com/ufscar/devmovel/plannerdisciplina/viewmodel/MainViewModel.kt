package com.ufscar.devmovel.plannerdisciplina.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufscar.devmovel.plannerdisciplina.data.repository.DisciplinaRepository
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.ui.state.EstadoAtualizacaoDisciplina
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import com.ufscar.devmovel.plannerdisciplina.model.DisciplinaCampos
import kotlinx.coroutines.launch
import kotlin.collections.remove

class MainViewModel(
    private val disciplinaRepository: DisciplinaRepository
) : ViewModel() {

    var disciplinaSendoEditada by mutableStateOf<Disciplina>(Disciplina())
    var estadoTemporarioDisciplina by mutableStateOf(EstadoAtualizacaoDisciplina())
//    val listaDisciplinas = disciplinaRepository.getAllDisciplinas()
    val listaDisciplinas = mutableStateListOf<DisciplinaCampos>()

    val listaTemporariaCamposDisciplina = mutableStateListOf<CampoDisciplina>()
    var adicaoCampoDialogAberto by mutableStateOf(false)
    var campoTemporario by mutableStateOf<CampoDisciplina>(CampoDisciplina(0, 0))
    var nomeCampoErro by mutableStateOf(false)
    var nomeCampoErroMensagem by mutableStateOf("")

    init {
        viewModelScope.launch {
            disciplinaRepository.getAllDisciplinaCampos().collect {
                listaDisciplinas.clear()
                it.forEach { disciplina -> listaDisciplinas.add(disciplina) }
            }
        }
    }

    fun adicionarDisciplina() {
        viewModelScope.launch {
            disciplinaRepository.insertDisciplina(Disciplina())
        }
    }

    fun removerDisciplina(disciplina: Disciplina) {
        viewModelScope.launch {
            disciplinaRepository.deleteDisciplina(disciplina)
        }
    }

    fun setarDisciplinaSendoEditada(disciplina: Disciplina) {
        disciplinaSendoEditada = disciplina
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina = disciplina.copy()
            )
    }

    fun setarCamposDisciplina(disciplinaId: Int) {
        viewModelScope.launch {
            disciplinaRepository.getCamposDisciplina(disciplinaId).collect {
                listaTemporariaCamposDisciplina.clear()
                it.forEach { campo -> listaTemporariaCamposDisciplina.add(campo) }
            }
        }
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

//    fun alterarEstadoTemporarioCampoValor(nome: String, valor: String) {
//        val novaLista = estadoTemporarioDisciplina.disciplina.campos.map { campo ->
//            if (campo.nome == nome) campo.copy(valor = valor)
//            else campo
//        }
//
//        estadoTemporarioDisciplina = estadoTemporarioDisciplina.copy(
//            disciplina = estadoTemporarioDisciplina.disciplina.copy(
//                campos = novaLista
//            )
//        )
//    }

    fun alterarCampoTemporarioNome(nome: String) {
        campoTemporario = campoTemporario.copy(nome = nome)
        nomeCampoErro = false
        nomeCampoErroMensagem = ""
    }

    fun alterarCampoTemporarioValor(valor: String) {
        campoTemporario = campoTemporario.copy(valor = valor)
    }

    fun salvarAlteracoesCampo() {
//        if (campoTemporario.nome.isEmpty()) {
//            nomeCampoErro = true
//            nomeCampoErroMensagem = "Nome do campo não pode ser vazio"
//            return
//        }
//
//        val campoDuplicado = estadoTemporarioDisciplina.disciplina.campos.find{
//            it.nome.equals(campoTemporario.nome, ignoreCase = true)
//        }
//        if (campoDuplicado != null) {
//            nomeCampoErro = true
//            nomeCampoErroMensagem = "Campo já existe"
//            return
//        }

//        val novoCampo = CampoDisciplina(campoTemporario.nome, campoTemporario.valor)
//        val novaLista = estadoTemporarioDisciplina.disciplina.campos.toMutableList()
//        novaLista.add(novoCampo)
//        estadoTemporarioDisciplina =
//            estadoTemporarioDisciplina.copy(
//                disciplina =
//                    estadoTemporarioDisciplina.disciplina.copy(
//                        campos = novaLista
//                    )
//            )
//        viewModelScope.launch {
//            disciplinaRepository.insertCampoDisciplina(campoTemporario)
//        }
        listaTemporariaCamposDisciplina.add(campoTemporario)
        fecharAdicaoCampoDialog()
    }
//
//    fun removerCampo(nome: String) {
//        val campo = estadoTemporarioDisciplina.disciplina.campos.find{ it.nome == nome }
//        val novaLista = estadoTemporarioDisciplina.disciplina.campos.toMutableList()
//        novaLista.remove(campo)
//        estadoTemporarioDisciplina =
//            estadoTemporarioDisciplina.copy(
//                disciplina =
//                    estadoTemporarioDisciplina.disciplina.copy(
//                        campos = novaLista
//                    )
//            )
//    }

    fun salvarAlteracoesDisciplina() {
        listaTemporariaCamposDisciplina.forEach { campo ->
            viewModelScope.launch {
                disciplinaRepository.insertCampoDisciplina(campo)
            }
        }
    }

    fun abrirAdicaoCampoDialog() {
        campoTemporario = CampoDisciplina(disciplinaId = disciplinaSendoEditada.id)
        adicaoCampoDialogAberto = true
    }

    fun fecharAdicaoCampoDialog() {
        adicaoCampoDialogAberto = false
    }

}