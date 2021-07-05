package pt.ipg.covid

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class TabelaPacientes(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria(){
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,$CAMPO_ID_VACINA NUMERIC NOT NULL ,$NOME_PACIENTE TEXT NOT NULL, $NASCIMENTO_PACIENTE DATE NOT NULL, $NR_PACIENTE STRING NOT NULL  ,FOREIGN KEY (${CAMPO_ID_VACINA}) REFERENCES ${TabelaVacinas.NOME_TABELA})")
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
        val ultimaColuna =columns.size -1

        var posColNomeVacina = -1
        var posColDataVacina = -1

        for(i in 0 ..ultimaColuna){
            if(columns[i] == CAMPO_EXTERNO_NOME_VACINA){
                posColNomeVacina = i
                break
            }else if(columns[i] == CAMPO_EXTERNO_DATA_VACINA){
                posColDataVacina = -1

            }

        }
        if (posColNomeVacina == -1 && posColDataVacina == -1){
            return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
        }
        var colunas = ""
        for(i in 0.. ultimaColuna){
            if( i > 0) colunas+=","

            colunas += if(i ==posColNomeVacina) {
                "${TabelaVacinas.NOME_TABELA}.${TabelaVacinas.NOME_VACINA} AS $CAMPO_EXTERNO_NOME_VACINA"
            }else if ( i == posColDataVacina){
                "${TabelaVacinas.NOME_TABELA}.${TabelaVacinas.CAMPO_DATA} AS $CAMPO_EXTERNO_DATA_VACINA"
            }else{
                "${NOME_TABELA}.${columns[i]}"
            }

        }
        val tabelas= "$NOME_TABELA INNER JOIN ${TabelaVacinas.NOME_TABELA} ON ${TabelaVacinas.NOME_TABELA}.${BaseColumns._ID}=$CAMPO_ID_VACINA"
               // "INNER JOIN ${TabelaVacinas.NOME_TABELA} ON ${TabelaVacinas.NOME_TABELA}.${BaseColumns._ID} = $CAMPO_EXTERNO_NOME_VACINA" +
               // "INNER JOIN ${TabelaVacinas.NOME_TABELA} ON ${TabelaVacinas.NOME_TABELA}.${BaseColumns._ID} = $CAMPO_EXTERNO_DATA_VACINA"

        var sql = "SELECT $colunas FROM $tabelas"

        if(selection != null) sql += "WHERE $selection"

        if(groupBy != null){
            sql += " GROUP BY $groupBy"
            if (having != null) " HAVING $having"
        }

        if(orderBy != null) sql += " ORDER BY $orderBy"

        return db.rawQuery(sql, selectionArgs)

    }


    companion object{
        const val NOME_TABELA = "pacientes"
        const val NOME_PACIENTE = "nome"
        const val NASCIMENTO_PACIENTE = "nascimento"
        const val NR_PACIENTE = "numero"
        const val CAMPO_ID_VACINA = "id_vacina"
        const val CAMPO_EXTERNO_NOME_VACINA = "nome_vacina"
        const val CAMPO_EXTERNO_DATA_VACINA = "data_vacina"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, NOME_PACIENTE, NASCIMENTO_PACIENTE, NR_PACIENTE, CAMPO_ID_VACINA,
            CAMPO_EXTERNO_NOME_VACINA, CAMPO_EXTERNO_DATA_VACINA)
    }
}




