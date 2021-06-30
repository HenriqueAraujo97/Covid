package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Paciente(var id: Long = -1, var nome: String, var nascimento: Date, var numero: String, var idVacina : Long, var nomeVacina: String? = null, var dataVacina: Date? = null) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues().apply {
            put(TabelaPacientes.NOME_PACIENTE,nome)
            put(TabelaPacientes.NASCIMENTO_PACIENTE,nascimento.time)
            put(TabelaPacientes.NR_PACIENTE,numero)
            put(TabelaPacientes.CAMPO_ID_VACINA,idVacina)
            put(TabelaPacientes.CAMPO_EXTERNO_NOME_VACINA,nomeVacina)
            put(TabelaPacientes.CAMPO_EXTERNO_DATA_VACINA,dataVacina!!.time)
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
            val colNomeVacina = cursor.getColumnIndex(TabelaPacientes.CAMPO_EXTERNO_NOME_VACINA)
            val colDataVacina = cursor.getColumnIndex(TabelaPacientes.CAMPO_EXTERNO_DATA_VACINA)

            val id= cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val nascimento = Date(cursor.getLong(colNascimento))
            val numero = cursor.getString(colNumero)
            val idVacina = cursor.getLong(colIVacina)
            val nomeVacina = if (colNomeVacina != -1) cursor.getString(colNomeVacina) else null
            val dataVacina = if (colDataVacina != -1) Date(cursor.getString(colDataVacina)) else null


            return Paciente(id ,nome ,nascimento , numero,idVacina,nomeVacina,dataVacina)

        }
    }
}