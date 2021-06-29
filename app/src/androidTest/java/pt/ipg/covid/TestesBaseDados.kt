package pt.ipg.covid

import android.provider.BaseColumns
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
            TabelaPacientes.TODAS_COLUNAS,
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
            TabelaPacientes.TODAS_COLUNAS,
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

        val vacina = Vacina( Date(2030 - 1900, 12, 10),nome = "Pfizer")
        vacina.id = insereVacinas(tabelaVacinas, vacina)

        assertEquals(vacina, getVacinaBaseDados(tabelaVacinas, vacina.id))

        db.close()
    }



}