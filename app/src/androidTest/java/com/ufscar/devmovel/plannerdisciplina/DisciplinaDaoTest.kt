package com.ufscar.devmovel.plannerdisciplina

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ufscar.devmovel.plannerdisciplina.data.AppDatabase
import com.ufscar.devmovel.plannerdisciplina.data.dao.DisciplinaDao
import com.ufscar.devmovel.plannerdisciplina.model.CampoDisciplina
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisciplinaDaoTest {
    private lateinit var disciplinaDao: DisciplinaDao
    private lateinit var appDatabase: AppDatabase
    private lateinit var disciplina1: Disciplina
    private lateinit var disciplina2: Disciplina
    private lateinit var campo1: CampoDisciplina
    private lateinit var campo2: CampoDisciplina
    private lateinit var campo3: CampoDisciplina
    private lateinit var campo4: CampoDisciplina

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        disciplinaDao = appDatabase.disciplinaDao()
        disciplina1 = Disciplina(
            id = 1,
            nome = "Matemática",
            progresso = 0.5f
        )
        disciplina2 = Disciplina(
            id = 2,
            nome = "Biologia",
            progresso = 0.6f
        )
        campo1 = CampoDisciplina(
            id = 1,
            disciplinaId = 1,
            nome = "Professor",
            valor = "André"
        )
        campo2 = CampoDisciplina(
            id = 1,
            disciplinaId = 1,
            nome = "Professor",
            valor = "Adriano"
        )
        campo3 = CampoDisciplina(
            id = 2,
            disciplinaId = 2,
            nome = "Sala",
            valor = "LE6"
        )
        campo4 = CampoDisciplina(
            id = 3,
            disciplinaId = 2,
            nome = "Professor",
            valor = "Anderson"
        )
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun test_insert_e_get_all_disciplinas() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina1)
        disciplinaDao.insertDisciplina(disciplina2)

        // Then
        val disciplinas = disciplinaDao.getAllDisciplinas().first()
        assertTrue(disciplinas.contains(disciplina1))
        assertTrue(disciplinas.contains(disciplina2))
        assertEquals(2, disciplinas.size)
    }

    @Test
    fun test_upsert_campo_disciplina() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina1)
        disciplinaDao.insertDisciplina(disciplina2)
        disciplinaDao.upsertCampoDisciplina(campo1)
        disciplinaDao.upsertCampoDisciplina(campo2)
        disciplinaDao.upsertCampoDisciplina(campo3)

        // Then
        val camposDisciplina1 = disciplinaDao.getCamposDisciplina(disciplina1.id).first()
        val camposDisciplina2 = disciplinaDao.getCamposDisciplina(disciplina2.id).first()
        assertEquals(1, camposDisciplina1.size)
        assertFalse(camposDisciplina1.contains(campo1))
        assertTrue(camposDisciplina1.contains(campo2))
        assertTrue(camposDisciplina2.contains(campo3))
        assertFalse(camposDisciplina1.contains(campo3))
        assertFalse(camposDisciplina2.contains(campo1))
        assertFalse(camposDisciplina2.contains(campo2))
    }

    @Test
    fun test_update_nome_disciplina() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina1)
        disciplinaDao.updateNomeDisciplina(1, "Biologia")

        // Then
        val disciplinaAtualizada = requireNotNull(disciplinaDao.getAllDisciplinas().first().find{ it.id == disciplina1.id })
        assertNotNull(disciplinaAtualizada)
        assertEquals("Biologia", disciplinaAtualizada.nome)
    }

    @Test
    fun test_get_all_disciplina_campos() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina1)
        disciplinaDao.insertDisciplina(disciplina2)
        disciplinaDao.upsertCampoDisciplina(campo1)
        disciplinaDao.upsertCampoDisciplina(campo3)
        disciplinaDao.upsertCampoDisciplina(campo4)

        // Then
        val todosDisciplinaCampos = disciplinaDao.getAllDisciplinaCampos().first()
        val disciplinaCampos1 = requireNotNull(todosDisciplinaCampos.find{ it.disciplina.id == disciplina1.id })
        val disciplinaCampos2 = requireNotNull(todosDisciplinaCampos.find{ it.disciplina.id == disciplina2.id })
        assertEquals(disciplina1, disciplinaCampos1.disciplina)
        assertEquals(disciplina2, disciplinaCampos2.disciplina)
        assertEquals(2, todosDisciplinaCampos.size)
        assertEquals(1, disciplinaCampos1.campos.size)
        assertEquals(2, disciplinaCampos2.campos.size)
        assertTrue(disciplinaCampos1.campos.contains(campo1))
        assertTrue(disciplinaCampos2.campos.contains(campo3))
        assertTrue(disciplinaCampos2.campos.contains(campo4))
        assertFalse(disciplinaCampos1.campos.contains(campo2))
        assertFalse(disciplinaCampos1.campos.contains(campo3))
        assertFalse(disciplinaCampos1.campos.contains(campo4))
        assertFalse(disciplinaCampos2.campos.contains(campo1))
        assertFalse(disciplinaCampos2.campos.contains(campo2))
    }

    @Test
    fun test_get_campos_disciplina() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina1)
        disciplinaDao.insertDisciplina(disciplina2)
        disciplinaDao.upsertCampoDisciplina(campo1)
        disciplinaDao.upsertCampoDisciplina(campo3)
        disciplinaDao.upsertCampoDisciplina(campo4)

        // Then
        val camposDisciplina1 = disciplinaDao.getCamposDisciplina(disciplina1.id).first()
        val camposDisciplina2 = disciplinaDao.getCamposDisciplina(disciplina2.id).first()
        assertEquals(1, camposDisciplina1.size)
        assertEquals(2, camposDisciplina2.size)
        assertTrue(camposDisciplina1.contains(campo1))
        assertTrue(camposDisciplina2.contains(campo3))
        assertTrue(camposDisciplina2.contains(campo4))
        assertFalse(camposDisciplina1.contains(campo2))
        assertFalse(camposDisciplina1.contains(campo3))
        assertFalse(camposDisciplina1.contains(campo4))
        assertFalse(camposDisciplina2.contains(campo1))
        assertFalse(camposDisciplina2.contains(campo2))
    }

    @Test
    fun test_delete_disciplina() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina1)
        disciplinaDao.insertDisciplina(disciplina2)
        disciplinaDao.deleteDisciplina(disciplina1)

        // Then
        val disciplinas = disciplinaDao.getAllDisciplinas().first()
        assertEquals(1, disciplinas.size)
        assertTrue(disciplinas.contains(disciplina2))
        assertFalse(disciplinas.contains(disciplina1))
    }

    @Test
    fun test_delete_campo_disciplina() = runTest {
        // When
        disciplinaDao.insertDisciplina(disciplina2)
        disciplinaDao.upsertCampoDisciplina(campo3)
        disciplinaDao.upsertCampoDisciplina(campo4)
        disciplinaDao.deleteCampoDisciplina(campo3)

        // Then
        val camposDisciplina = disciplinaDao.getCamposDisciplina(disciplina2.id).first()
        assertEquals(1, camposDisciplina.size)
        assertTrue(camposDisciplina.contains(campo4))
        assertFalse(camposDisciplina.contains(campo1))
        assertFalse(camposDisciplina.contains(campo2))
        assertFalse(camposDisciplina.contains(campo3))
    }
}
