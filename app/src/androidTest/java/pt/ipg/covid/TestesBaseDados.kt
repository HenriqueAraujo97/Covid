package pt.ipg.covid

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestesBaseDados {
    private  fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private  fun getBdCovidOpenHelper() = BdCovidOpenHelper(getAppContext())

    private fun inserePacientes(tabela: TabelaPacientes, pacientes:Pacientes):Long{
        val id = tabela.insert(pacientes.toContentValues())
        assertNotEquals(-1,id)

        return id
    }

    private fun getPacienteBaseDAdos(tabela: TabelaPacientes, id: Long):Pacientes{
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

}