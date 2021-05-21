package pt.ipg.covid

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaEnfermeiros(db: SQLiteDatabase) {
    private val db : SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $NOME_ENFERMEIRO TEXT NOT NULL, $NR_ENFERMEIRO INTEGER NOT NULL, FOREIGN KEY ($CAMPO_ID_ENFERMEIRO) REFERENCES ${TabelaVacinas.NOME_TABELA})")
    }

    companion object{
        const val NOME_TABELA = "enfermeiros"
        const val NOME_ENFERMEIRO = "nome"
        const val NR_ENFERMEIRO = "numero"
        const val CAMPO_ID_ENFERMEIRO = "id_enfermeiro"

    }
}