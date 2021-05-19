package pt.ipg.covid

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class TabelaPacientes(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $NOME_PACIENTE TEXT NOT NULL, $IDADE_PACIENTE INTEGER NOT NULL, $NR_PACIENTE INTEGER NOT NULL)")
    }





    companion object{
        const val NOME_TABELA = "pacientes"
        const val NOME_PACIENTE = "nome"
        const val IDADE_PACIENTE = "idade"
        const val NR_PACIENTE = "numero"
    }
}




