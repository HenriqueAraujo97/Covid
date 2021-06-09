package pt.ipg.covid

import android.content.ContentValues
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
}