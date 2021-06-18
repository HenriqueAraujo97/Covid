package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class TabelaPacientes(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,$CAMPO_ID_VACINA NUMERIC NOT NULL ,$NOME_PACIENTE TEXT NOT NULL, $NASCIMENTO_PACIENTE DATE NOT NULL, $NR_PACIENTE STRING NOT NULL,  FOREIGN KEY (${CAMPO_ID_VACINA}) REFERENCES ${TabelaVacinas.NOME_TABELA})")
    }

    fun insert(values: ContentValues): Long{
        return  db.insert(NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }


    companion object{
        const val NOME_TABELA = "pacientes"
        const val NOME_PACIENTE = "nome"
        const val NASCIMENTO_PACIENTE = "nascimento"
        const val NR_PACIENTE = "numero"
        const val CAMPO_ID_VACINA = "id_vacina"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, NOME_PACIENTE, NASCIMENTO_PACIENTE, NR_PACIENTE, CAMPO_ID_VACINA)
    }
}




