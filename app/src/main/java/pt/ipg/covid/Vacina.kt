package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.time.LocalDate
import java.util.*

data class Vacina(var id: Long = -1, var data: Date, var nome: String) {
    fun toContentValues() : ContentValues{
        val valores = ContentValues().apply {
            put(TabelaVacinas.CAMPO_DATA,data.time)
            put(TabelaVacinas.NOME_VACINA,nome)


        }
        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor) : Vacina{
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colData = cursor.getColumnIndex(TabelaVacinas.CAMPO_DATA)

            val colNome = cursor.getColumnIndex(TabelaVacinas.NOME_VACINA)

            val id = cursor.getLong(colId)
            val data = Date(cursor.getLong(colData))
            val nome = cursor.getString(colNome)


            return Vacina( id, data, nome)
        }
    }
}