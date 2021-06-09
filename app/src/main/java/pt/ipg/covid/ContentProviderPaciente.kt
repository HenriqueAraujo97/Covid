package pt.ipg.covid

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
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
        TODO("Not yet implemented")
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

        private  const val MULTIPLOS_ITEMS = "vnd.android.cursor.dir"
        private  const val UNICO_ITEMS = "vnd.android.cursor.item"

        private val ENDEREÇO_BASE = Uri.parse("content://$AUTHORITY")
        private val ENDEREÇO_PACIENTES = Uri.withAppendedPath(ENDEREÇO_BASE, PACIENTES)
        private val ENDEREÇO_ENFERMEIROS = Uri.withAppendedPath(ENDEREÇO_BASE, ENFERMEIROS)
        private val ENDEREÇO_VACINAS = Uri.withAppendedPath(ENDEREÇO_BASE, VACINAS)
    }

}