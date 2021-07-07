package pt.ipg.covid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class vacinas : AppCompatActivity() {
    val dbHandler = BdCovidOpenHelper(this, null)
    var dataListVacinas = ArrayList<HashMap<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacinas)
    }


    fun loadIntoList(){

        dataListVacinas.clear()
        val cursor = dbHandler.getAllRowVacinas()
        cursor!!.moveToFirst()

        while (!cursor.isAfterLast) {
            val map = HashMap<String, String>()
            map["id_vacina"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_ID_VACINA))
            map["name_vacina"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_NAME_VACINA))
            map["nr_vacina"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_NR_VACINA))
            map["validade_vacina"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_VALIDADE_VACINA))
            dataListVacinas.add(map)

            cursor.moveToNext()
        }
        findViewById<ListView>(R.id.listView_Vacinas).adapter = CustomAdapterVacina(this@vacinas, dataListVacinas)
        findViewById<ListView>(R.id.listView_Vacinas).setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(this, lista_vacinas::class.java)
            intent.putExtra("id_vacina", dataListVacinas[+i]["id_vacina"])
            intent.putExtra("name_vacina", dataListVacinas[+i]["name_vacina"])
            intent.putExtra("nr_vacina", dataListVacinas[+i]["nr_vacina"])
            intent.putExtra("validade_vacina", dataListVacinas[+i]["validade_vacina"])
            startActivity(intent)
        }
    }

    fun fabClickedVacinas(v: View){
        val intent = Intent(this, lista_vacinas::class.java)
        startActivity(intent)
    }
    public override fun onResume() {
        super.onResume()
        loadIntoList()
    }


}