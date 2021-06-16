package pt.ipg.covid

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import kotlin.reflect.KTypeProjection

class ContentProviderPaciente : ContentProvider() {
    private var bdCovidOpenHelper : BdCovidOpenHelper? = null
    override fun onCreate(): Boolean {
        bdCovidOpenHelper = BdCovidOpenHelper(context)
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

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
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