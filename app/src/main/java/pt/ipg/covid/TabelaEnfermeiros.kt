package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaEnfermeiros(db: SQLiteDatabase) {
    private val db : SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $NOME_ENFERMEIRO TEXT NOT NULL, $NR_ENFERMEIRO INTEGER NOT NULL, FOREIGN KEY ($CAMPO_ID_ENFERMEIRO) REFERENCES ${TabelaVacinas.NOME_TABELA})")
    }

    fun insert(values: ContentValues): Long {
        return db.insert(TabelaVacinas.NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(TabelaVacinas.NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(TabelaVacinas.NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {
        return db.query(TabelaVacinas.NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }



    companion object{
        const val NOME_TABELA = "enfermeiros"
        const val NOME_ENFERMEIRO = "nome"
        const val NR_ENFERMEIRO = "numero"
        const val CAMPO_ID_ENFERMEIRO = "id_enfermeiro"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID,
            TabelaEnfermeiros.NOME_ENFERMEIRO,
            TabelaEnfermeiros.NR_ENFERMEIRO,
            TabelaEnfermeiros.CAMPO_ID_ENFERMEIRO
        )

    }
}