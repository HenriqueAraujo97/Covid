package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Paciente(var id: Long = -1, var nome: String, var nascimento: String, var numero: Long, var idVacina : Long) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues().apply {
            put(TabelaPacientes.NOME_PACIENTE,nome)
            put(TabelaPacientes.NASCIMENTO_PACIENTE,nascimento)
            put(TabelaPacientes.NR_PACIENTE,numero)
            put(TabelaPacientes.CAMPO_ID_VACINA,idVacina)
        }
        return valores
    }


    companion object{
        fun fromCursor(cursor: Cursor): Paciente{
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaPacientes.NOME_PACIENTE)
            val colNascimento = cursor.getColumnIndex(TabelaPacientes.NASCIMENTO_PACIENTE)
            val colINumero = cursor.getColumnIndex(TabelaPacientes.NR_PACIENTE)
            val colIVacina = cursor.getColumnIndex(TabelaPacientes.CAMPO_ID_VACINA)

            val id= cursor.getLong(colId)
            val nome = cursor.getLong(colNome)
            val numeo = cursor.getLong(colNumero)
            val idVacina = cursor.getLong(colIVacina)

            return Paciente(id,nome,nascimento , numero,idVacina)

        }
    }
}