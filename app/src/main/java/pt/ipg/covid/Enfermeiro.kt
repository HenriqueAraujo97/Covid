package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Enfermeiro(var id: Long = -1, var nomeEnf: String , var numeroEnf: Long, var idEnfermeiro: Long) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues().apply {
            put(TabelaEnfermeiros.NOME_ENFERMEIRO, nomeEnf)
            put(TabelaEnfermeiros.NR_ENFERMEIRO, numeroEnf)
            put(TabelaEnfermeiros.CAMPO_ID_ENFERMEIRO, idEnfermeiro)
    }
        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor) : Enfermeiro {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNomeEnf = cursor.getColumnIndex(TabelaEnfermeiros.NOME_ENFERMEIRO)
            val colNumeroEnf = cursor.getColumnIndex(TabelaEnfermeiros.NR_ENFERMEIRO)
            val colIdEnfermeiro = cursor.getColumnIndex(TabelaEnfermeiros.CAMPO_ID_ENFERMEIRO)

            val id= cursor.getLong(colId)
            val nomeEnf= cursor.getString(colNomeEnf)
            val numeroEnf= cursor.getLong(colNomeEnf)
            val idEnfermeiro= cursor.getLong(colIdEnfermeiro)

            return Enfermeiro(id, nomeEnf ,numeroEnf,idEnfermeiro)
        }
    }
}