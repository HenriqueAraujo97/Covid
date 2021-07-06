package pt.ipg.covid

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import kotlin.reflect.KTypeProjection

class ContentProviderCovid : ContentProvider() {
    private var bdCovidOpenHelper : BdCovidOpenHelper? = null
    override fun onCreate(): Boolean {
       // bdCovidOpenHelper = BdCovidOpenHelper(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        seletionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val bd = bdCovidOpenHelper!!.readableDatabase

        return when (getUriMatcher().match(uri)){
            URI_PACIENTES -> TabelaPacientes(bd).query(
                projection as Array<String>,
                selection,
                seletionArgs as Array<String>?,
                null,
                null,
                sortOrder
            )
            URI_PACIENTE_ESPECIFICO -> TabelaPacientes(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!),
                null,
                null,
                null
            )

            URI_ENFERMEIROS -> TabelaEnfermeiros(bd).query(
                projection as Array<String>,
                selection,
                seletionArgs as Array<String>?,
                null,
                null,
                sortOrder
            )

            URI_ENFERMEIRO_ESPECIFICO -> TabelaEnfermeiros(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!),
                null,
                null,
                null
            )

            URI_VACINAS -> TabelaVacinas(bd).query(
                projection as Array<String>,
                selection,
                seletionArgs as Array<String>?,
                null,
                null,
                sortOrder
            )

            URI_VACIA_EPECIFICA -> TabelaVacinas(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!),
                null,
                null,
                null
            )

            else -> null

        }
    }

    override fun getType(uri: Uri): String? {
       return when (getUriMatcher().match(uri)){
           URI_PACIENTES -> "$MULTIPLOS_ITEMS/$PACIENTES"
           URI_PACIENTE_ESPECIFICO -> "$UNICO_ITEMS/$PACIENTES"
           URI_ENFERMEIROS -> "$MULTIPLOS_ITEMS/$ENFERMEIROS"
           URI_ENFERMEIRO_ESPECIFICO -> "$UNICO_ITEMS/$ENFERMEIROS"
           URI_VACINAS -> "$MULTIPLOS_ITEMS/$VACINAS"
           URI_VACIA_EPECIFICA -> "$UNICO_ITEMS/$VACINAS"
           else -> null
       }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val bd = bdCovidOpenHelper!!.writableDatabase

        val id = when (getUriMatcher().match(uri)){
            URI_PACIENTES -> TabelaPacientes(bd).insert(values!!)
            URI_ENFERMEIROS -> TabelaEnfermeiros(bd).insert(values!!)
            URI_VACINAS -> TabelaVacinas(bd).insert(values!!)
            else -> -1L
        }

        if( id == -1L) return null

        return Uri.withAppendedPath(uri,id.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdCovidOpenHelper!!.writableDatabase

        return when (getUriMatcher().match(uri)){
            URI_PACIENTE_ESPECIFICO -> TabelaPacientes(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!)
            )
            URI_ENFERMEIRO_ESPECIFICO -> TabelaEnfermeiros(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!)
            )
            URI_VACIA_EPECIFICA -> TabelaVacinas(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!)
            )
            else -> 0

        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdCovidOpenHelper!!.writableDatabase

        return  when (getUriMatcher().match(uri)){
            URI_PACIENTE_ESPECIFICO -> TabelaPacientes(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!)
            )
            URI_ENFERMEIRO_ESPECIFICO -> TabelaEnfermeiros(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!)
            )
            URI_VACIA_EPECIFICA -> TabelaVacinas(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!)
            )
            else -> 0
        }
    }

    companion object{
        private const val AUTHORITY= "pt.ipg.covid"

        private const val PACIENTES = "pacientes"
        private const val ENFERMEIROS = "enfermeiros"
        private const val VACINAS = "vacinas"

        private const val URI_PACIENTES = 100
        private const val URI_PACIENTE_ESPECIFICO = 101
        private const val URI_ENFERMEIROS = 200
        private const val URI_ENFERMEIRO_ESPECIFICO = 201
        private const val URI_VACINAS = 300
        private const val URI_VACIA_EPECIFICA = 301


        private  const val MULTIPLOS_ITEMS = "vnd.android.cursor.dir"
        private  const val UNICO_ITEMS = "vnd.android.cursor.item"

        private val ENDEREÇO_BASE = Uri.parse("content://$AUTHORITY")
        private val ENDEREÇO_PACIENTES = Uri.withAppendedPath(ENDEREÇO_BASE, PACIENTES)
        private val ENDEREÇO_ENFERMEIROS = Uri.withAppendedPath(ENDEREÇO_BASE, ENFERMEIROS)
        private val ENDEREÇO_VACINAS = Uri.withAppendedPath(ENDEREÇO_BASE, VACINAS)

        private fun getUriMatcher() : UriMatcher{
            val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, PACIENTES,URI_PACIENTES)
            uriMatcher.addURI(AUTHORITY, "$PACIENTES/#", URI_PACIENTE_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, ENFERMEIROS, URI_ENFERMEIROS)
            uriMatcher.addURI(AUTHORITY, "$ENFERMEIROS/#", URI_ENFERMEIRO_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, VACINAS, URI_VACINAS)
            uriMatcher.addURI(AUTHORITY, "$VACINAS/#", URI_VACIA_EPECIFICA)

            return uriMatcher
        }
    }

}