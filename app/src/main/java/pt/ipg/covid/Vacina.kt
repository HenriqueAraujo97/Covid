package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Vacina(var id: Long = -1, var data:  Date, var local: String, var idVacina: Long) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues().apply {
            put(TabelaVacinas.CAMPO_DATA,data)
            put(TabelaVacinas.CAMPO_LOCAL,local)
            put(TabelaVacinas.CAMPO_ID_PACIENTE,idVacina)
        }
        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor) : Vacina{
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colData = cursor.getColumnIndex(TabelaVacinas.CAMPO_DATA)
            val colLocal = cursor.getColumnIndex(TabelaVacinas.CAMPO_LOCAL)
            val colIdPaciente = cursor.getColumnIndex(TabelaVacinas.CAMPO_ID_PACIENTE)

            val id = cursor.getLong(colId)
            val data = cursor.getLong(colData)
            val local = cursor.getString(colLocal)
            val idVacina = cursor.getLong(colIdPaciente)

            return Vacina( id, data, local, idVacina)
        }
    }
}