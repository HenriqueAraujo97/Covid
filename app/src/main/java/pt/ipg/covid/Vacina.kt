package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.time.LocalDate
import java.util.*

data class Vacina(var id: Long = -1, var data: Date, var local: String) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues().apply {
            put(TabelaVacinas.CAMPO_DATA,data.time)
            put(TabelaVacinas.CAMPO_LOCAL,local)
            //put(TabelaVacinas.CAMPO_ID_PACIENTE,idPaciente)
        }
        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor) : Vacina{
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colData = cursor.getColumnIndex(TabelaVacinas.CAMPO_DATA)
            val colLocal = cursor.getColumnIndex(TabelaVacinas.CAMPO_LOCAL)
           // val colIdPaciente = cursor.getColumnIndex(TabelaVacinas.CAMPO_ID_PACIENTE)

            val id = cursor.getLong(colId)
            val data = Date(cursor.getLong(colData))
            val local = cursor.getString(colLocal)
            //val idPaciente = cursor.getLong(colIdPaciente)

            return Vacina( id, data, local) //idPaciente)
        }
    }
}