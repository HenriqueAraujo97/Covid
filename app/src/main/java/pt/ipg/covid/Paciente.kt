package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Paciente(var id: Long = -1, var nome: String, var nascimento: Date, var numero: String, var idVacina : Long) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues().apply {
            put(TabelaPacientes.NOME_PACIENTE,nome)
            put(TabelaPacientes.NASCIMENTO_PACIENTE,nascimento.time)
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
            val colNumero = cursor.getColumnIndex(TabelaPacientes.NR_PACIENTE)
            val colIVacina = cursor.getColumnIndex(TabelaPacientes.CAMPO_ID_VACINA)

            val id= cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val nascimento = Date(cursor.getLong(colNascimento))
            val numero = cursor.getString(colNumero)
            val idVacina = cursor.getLong(colIVacina)

            return Paciente(id ,nome ,nascimento , numero,idVacina)

        }
    }
}