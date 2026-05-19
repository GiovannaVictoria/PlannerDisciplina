package com.ufscar.devmovel.plannerdisciplina.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufscar.devmovel.plannerdisciplina.ZenQuotesRepository
import com.ufscar.devmovel.plannerdisciplina.data.repository.DisciplinaRepository
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.ui.state.EstadoAtualizacaoDisciplina
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import com.ufscar.devmovel.plannerdisciplina.model.DisciplinaCampos
import kotlinx.coroutines.launch

class MainViewModel(
    private val disciplinaRepository: DisciplinaRepository,
    private val zenQuotesRepository: ZenQuotesRepository
) : ViewModel() {
    var estadoTemporarioDisciplina by mutableStateOf(EstadoAtualizacaoDisciplina())
    val listaDisciplinas = mutableStateListOf<DisciplinaCampos>()

    val listaTemporariaCamposDisciplina = mutableStateListOf<CampoDisciplina>()
    val listaRemocaoCamposDisciplina = mutableStateListOf<CampoDisciplina>()
    var adicaoCampoDialogAberto by mutableStateOf(false)
    var removerDisciplinaDialogAberto by mutableStateOf(false)
    var cancelarAlteracoesCamposDialogAberto by mutableStateOf(false)
    var campoTemporario by mutableStateOf<CampoDisciplina>(CampoDisciplina(0, 0))
    var nomeCampoErro by mutableStateOf(false)
    var nomeCampoErroMensagem by mutableStateOf("")
    var quoteContent by mutableStateOf("")
    var quoteAuthor by mutableStateOf("")
    var formattedQuote by mutableStateOf("")

    init {
        viewModelScope.launch {
            disciplinaRepository.getAllDisciplinaCampos().collect {
                listaDisciplinas.clear()
                it.forEach { disciplina -> listaDisciplinas.add(disciplina) }
            }
        }
        viewModelScope.launch {
            try {
                var quote = zenQuotesRepository.getQuote()
                while (quote.content.length > 160) {
                    quote = zenQuotesRepository.getQuote()
                }
                quoteContent = quote.content
                quoteAuthor = quote.author
                formattedQuote = formatarZenQuote(quoteContent, quoteAuthor)
            } catch (e: Exception) {
                formattedQuote = "Giovanna Victoria Rossetto"
            }
        }
    }

    fun formatarZenQuote(content: String, author: String): String {
        var formattedQuote = '"' + content[0].uppercase() + content.substring(1).lowercase()
        if (formattedQuote.last() != '.') {
            formattedQuote += '.'
        }
        formattedQuote += '"'
        formattedQuote += '\n'
        formattedQuote += "- "
        formattedQuote += author
        return formattedQuote
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
        removerDisciplinaDialogAberto = false
    }

    fun setarEstadoTemporarioDisciplina(disciplina: Disciplina) {
        estadoTemporarioDisciplina =
            estadoTemporarioDisciplina.copy(
                disciplina = disciplina.copy()
            )
    }

    fun setarCamposDisciplina(disciplinaId: Int) {
        listaRemocaoCamposDisciplina.clear()
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

    fun alterarEstadoTemporarioCampoValor(campoId: Int, novoValor: String) {
        val indice = listaTemporariaCamposDisciplina.indexOfFirst { it.id == campoId }

        if (indice != -1) {
            listaTemporariaCamposDisciplina[indice] =
                listaTemporariaCamposDisciplina[indice].copy(
                        valor = novoValor
                )
        }
    }

    fun alterarCampoTemporarioNome(nome: String) {
        campoTemporario = campoTemporario.copy(nome = nome)
        nomeCampoErro = false
        nomeCampoErroMensagem = ""
    }

    fun alterarCampoTemporarioValor(valor: String) {
        campoTemporario = campoTemporario.copy(valor = valor)
    }

    fun salvarAlteracoesCampo() {
        if (campoTemporario.nome.isEmpty()) {
            nomeCampoErro = true
            nomeCampoErroMensagem = "Nome do campo não pode ser vazio"
            return
        }

        val campoDuplicado =
            listaTemporariaCamposDisciplina.find {
                it.nome.equals(campoTemporario.nome, ignoreCase = true)
            }
        if (campoDuplicado != null) {
            nomeCampoErro = true
            nomeCampoErroMensagem = "Campo já existe"
            return
        }

        listaTemporariaCamposDisciplina.add(campoTemporario)
        fecharAdicaoCampoDialog()
    }

    fun removerCampo(campo: CampoDisciplina) {
        listaTemporariaCamposDisciplina.remove(campo)
        listaRemocaoCamposDisciplina.add(campo)
    }

    fun salvarAlteracoesDisciplina() {
        viewModelScope.launch {
            disciplinaRepository.updateNomeDisciplina(estadoTemporarioDisciplina.disciplina.id, estadoTemporarioDisciplina.disciplina.nome)
        }
        listaTemporariaCamposDisciplina.forEach { campo ->
            viewModelScope.launch {
                disciplinaRepository.upsertCampoDisciplina(campo)
            }
        }
        listaRemocaoCamposDisciplina.forEach { campo ->
            viewModelScope.launch {
                disciplinaRepository.deleteCampoDisciplina(campo)
            }
        }
    }

//    fun salvarAlteracoesDisciplina() {
//        viewModelScope.launch {
//            disciplinaRepository.updateNomeDisciplina(estadoTemporarioDisciplina.disciplina.id, estadoTemporarioDisciplina.disciplina.nome)
//            listaTemporariaCamposDisciplina.forEach { campo ->
//                disciplinaRepository.upsertCampoDisciplina(campo)
//            }
//            listaRemocaoCamposDisciplina.forEach { campo ->
//                disciplinaRepository.deleteCampoDisciplina(campo)
//            }
//        }
//    }

    fun abrirAdicaoCampoDialog() {
        campoTemporario = CampoDisciplina(disciplinaId = estadoTemporarioDisciplina.disciplina.id)
        adicaoCampoDialogAberto = true
    }

    fun fecharAdicaoCampoDialog() {
        adicaoCampoDialogAberto = false
    }

    fun abrirRemoverDisciplinaDialog() {
        removerDisciplinaDialogAberto = true
    }

    fun fecharRemoverDisciplinaDialog() {
        removerDisciplinaDialogAberto = false
    }

    fun abrirCancelarAlteracoesCamposDialog() {
        cancelarAlteracoesCamposDialogAberto = true
    }

    fun fecharCancelarAlteracoesCamposDialog() {
        cancelarAlteracoesCamposDialogAberto = false
    }

}