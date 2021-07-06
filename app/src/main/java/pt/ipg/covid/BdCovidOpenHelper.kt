package pt.ipg.covid

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class BdCovidOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_TABLE_USERS = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AGE TEXT, $COLUMN_EMAIL TEXT)"

        val CREATE_TABLE_PACIENTES = "CREATE TABLE $TABLE_NAME_PACIENTES" +
                "($COLUMN_ID_PACIENTE INTEGER PRIMARY KEY, $COLUMN_NAME_PACIENTE TEXT, $COLUMN_AGE_PACIENTE TEXT, $COLUMN_EMAIL_PACIENTE TEXT)"

        db!!.execSQL(CREATE_TABLE_USERS)
       db!!.execSQL(CREATE_TABLE_PACIENTES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_PACIENTES")

        onCreate(db)
    }

    fun insertRow(name: String, age:String, email: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_AGE, age)
        values.put(COLUMN_EMAIL, email)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun insertRowPacientes(name_paciente: String, age_paciente:String, email_paciente: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME_PACIENTE, name_paciente)
        values.put(COLUMN_AGE_PACIENTE, age_paciente)
        values.put(COLUMN_EMAIL_PACIENTE, email_paciente)

        val db = this.writableDatabase
        db.insert(TABLE_NAME_PACIENTES, null, values)
        db.close()
    }

    fun updateRow(row_id: String, name: String, age:String, email: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_AGE, age)
        values.put(COLUMN_EMAIL, email)



        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(row_id))
        db.close()
    }

    fun updateRowPacientes(row_id: String, name_paciente:  String, age_paciente: String, email_paciente: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME_PACIENTE, name_paciente)
        values.put(COLUMN_AGE_PACIENTE, age_paciente)
        values.put(COLUMN_EMAIL_PACIENTE, email_paciente)



        val db = this.writableDatabase
        db.update(TABLE_NAME_PACIENTES, values, "$COLUMN_ID = ?", arrayOf(row_id))
        db.close()
    }

    fun deleteRow(row_id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(row_id))
        db.close()
    }

    fun deleteRowPacientes(row_id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME_PACIENTES, "$COLUMN_ID = ?", arrayOf(row_id))
        db.close()
    }

    fun getAllRow(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getAllRowPaciente(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME_PACIENTES", null)
    }

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "myDBfile.db"
        const val TABLE_NAME = "users"
        const val TABLE_NAME_PACIENTES = "pacientes"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_EMAIL = "email"

        const val COLUMN_ID_PACIENTE = "id_paciente"
        const val COLUMN_NAME_PACIENTE = "name_paciente"
        const val COLUMN_AGE_PACIENTE = "age_paciente"
        const val COLUMN_EMAIL_PACIENTE = "email_paciente"
    }


}