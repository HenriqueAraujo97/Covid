package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaVacinas(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_DATA DATE NOT NULL, $CAMPO_LOCAL TEXT NOT NULL, FOREIGN KEY ($CAMPO_ID_PACIENTE) REFERENCES ${TabelaPacientes.NOME_TABELA} )")
    }

    fun insert(values: ContentValues): Long {
        return db.insert(TabelaPacientes.NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(TabelaPacientes.NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(TabelaPacientes.NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(TabelaPacientes.NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }



    companion object{
        const val NOME_TABELA = "vacinas"
        const val CAMPO_DATA = "data"
        const val CAMPO_LOCAL = "local"
        const val CAMPO_ID_PACIENTE = "id_paciente"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID,
            TabelaVacinas.CAMPO_DATA,
            TabelaVacinas.CAMPO_LOCAL,
            TabelaVacinas.CAMPO_ID_PACIENTE
        )
    }


}