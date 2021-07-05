package pt.ipg.covid

import android.provider.BaseColumns
import android.provider.VoicemailContract
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestesBaseDados {

    private  fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private  fun getBdCovidOpenHelper() = BdCovidOpenHelper(getAppContext())

    private fun Data(ano: Int, mes: Int, dia: Int) = Date(ano - 1900, mes, dia)



    private fun inserePacientes(tabela: TabelaPacientes, pacientes: Paciente):Long{
        val id = tabela.insert(pacientes.toContentValues())
        assertNotEquals(-1,id)


        return id
    }

    private fun insereEnfermeiros(tabela: TabelaEnfermeiros, enfermeiros: Enfermeiro):Long{
        val id = tabela.insert(enfermeiros.toContentValues())
        assertNotEquals(-1,id)


        return id
    }

    private fun insereVacinas(tabela: TabelaVacinas, vacinas: Vacina):Long{
        val id = tabela.insert(vacinas.toContentValues())
        assertNotEquals(-1,id)


        return id
    }


    private fun getPacienteBaseDados(tabela: TabelaPacientes, id: Long):Paciente{
        val cursor = tabela.query(
            TabelaPacientes.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Paciente.fromCursor(cursor)
    }

    private fun getEnfermeiroBaseDados(tabela: TabelaEnfermeiros, id: Long):Enfermeiro{
        val cursor = tabela.query(
            TabelaEnfermeiros.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Enfermeiro.fromCursor(cursor)
    }

    private fun getVacinaBaseDados(tabela: TabelaVacinas, id: Long):Vacina{
        val cursor = tabela.query(
            TabelaVacinas.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )
        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Vacina.fromCursor(cursor)
    }

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BdCovidOpenHelper.NOME_BASE_DADOS)
    }



    @Test
    fun consegueAbrirBaseDados() {
        val db = getBdCovidOpenHelper().readableDatabase
        assert(db.isOpen)
        db.close()
    }


    @Test
    fun consegueInserirVacinas(){
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaVacinas = TabelaVacinas(db)

        val vacina = Vacina( data = Date(2023 - 1900,12,12),nome = "Pfizer")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        assertEquals(vacina, getVacinaBaseDados(tabelaVacinas, vacina.id))

        db.close()
    }

    @Test
    fun consegueAlterarVacinas(){
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaVacinas = TabelaVacinas(db)

        val vacina = Vacina(data = Date(2024,8,21), nome = "Moderna")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        vacina.nome = "Sputnik"

        val registosAlterados = tabelaVacinas.update(
            vacina.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(vacina.id.toString())
        )

        assertEquals(1,registosAlterados)

        assertEquals(vacina,getVacinaBaseDados(tabelaVacinas,vacina.id))

        db.close()


    }

    @Test
    fun consegueEliminarVacinas() {
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaVacinas = TabelaVacinas(db)

        val vacina = Vacina(data = Date(2022,10,29),nome = "teste")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        val registosEliminados = tabelaVacinas.delete(
            "${BaseColumns._ID}=?",
            arrayOf(vacina.id.toString())
        )

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerVacinas() {
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaVacinas = TabelaVacinas(db)

        val vacina = Vacina(data = Date(2028,7,5),nome = "Aventura")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        assertEquals(vacina, getVacinaBaseDados(tabelaVacinas, vacina.id))

        db.close()
    }

    @Test
    fun consegueInserirPacientes(){
        val db = getBdCovidOpenHelper().writableDatabase

        val tabelaVacinas = TabelaVacinas(db)
        val vacina = Vacina(data = Date(2028 - 1901,7,5),nome = "Aventura")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        val tabelaPacientes = TabelaPacientes(db)
        val paciente = Paciente(
            nome = " Henrique Araujo",
            nascimento = Date(1997 - 1900,7,5),
            numero = "934345012",
            idVacina = vacina.id,
            nomeVacina = vacina.nome,
            dataVacina = vacina.data

        )
        paciente.id = inserePacientes(tabelaPacientes, paciente)

        assertEquals(paciente, getPacienteBaseDados(tabelaPacientes, paciente.id))

    }

   /* @Test
    fun consegueInserirEnfermeiros(){
        val db = getBdCovidOpenHelper().writableDatabase

        val tabelaVacinas = TabelaVacinas(db)
        val vacina = Vacina(data = Date(2028 - 1901,7,5),nome = "Aventura")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        val tabelaEnfermeiro = TabelaEnfermeiros(db)
        val enfermeiro = Enfermeiro(
            nomeEnf = " Antonio Pereira",
            numeroEnf = "934345012",
            idVacina = vacina.id

        )
        enfermeiro.id = insereEnfermeiros(tabelaEnfermeiro, enfermeiro)

        assertEquals(enfermeiro, getVacinaBaseDados(tabelaVacinas, enfermeiro.id))

    }
*/


}