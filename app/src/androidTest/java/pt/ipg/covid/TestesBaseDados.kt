package pt.ipg.covid

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
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

    @Test
    fun consegueAbrirBaseDados() {
        val db = getBdCovidOpenHelper().readableDatabase
        assert(db.isOpen)
        db.close()
    }

    private fun inserePacientes(tabela: TabelaPacientes, pacientes: Paciente):Long{
        val id = tabela.insert(pacientes.toContentValues())
        assertNotEquals(-1,id)

        Date(2020 - 1900, 12, 1)

        return id
    }

    private fun getPacienteBaseDAdos(tabela: TabelaPacientes, id: Long):Paciente{
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


   private fun insereVacinas(tabela: TabelaVacinas, vacinas:Vacina):Long{
        val id = tabela.insert(vacinas.toContentValues())
        assertNotEquals(-1,id)

        return id
    }

    private fun getVacinasBaseDAdos(tabela: TabelaVacinas, id: Long):Vacina{
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

}