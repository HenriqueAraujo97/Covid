package pt.ipg.covid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class activity_paciente : AppCompatActivity() {
    val dbHandler = BdCovidOpenHelper(this, null)
    var dataListPaciente = ArrayList<HashMap<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)
    }


    fun loadIntoList(){

        dataListPaciente.clear()
        val cursor = dbHandler.getAllRowPaciente()
        cursor!!.moveToFirst()

        while (!cursor.isAfterLast) {
            val map = HashMap<String, String>()
            map["id_paciente"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_ID_PACIENTE))
            map["name_paciente"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_NAME_PACIENTE))
            map["age_paciente"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_AGE_PACIENTE))
            map["email_paciente"] = cursor.getString(cursor.getColumnIndex(BdCovidOpenHelper.COLUMN_EMAIL_PACIENTE))
            dataListPaciente.add(map)

            cursor.moveToNext()
        }
        findViewById<ListView>(R.id.listView_Pacientes).adapter = CustomAdapterPaciente(this@activity_paciente, dataListPaciente)
        findViewById<ListView>(R.id.listView_Pacientes).setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(this, lista_pacientes::class.java)
            intent.putExtra("id_paciente", dataListPaciente[+i]["id_paciente"])
            intent.putExtra("name_paciente", dataListPaciente[+i]["name_paciente"])
            intent.putExtra("age_paciente", dataListPaciente[+i]["age_paciente"])
            intent.putExtra("email_paciente", dataListPaciente[+i]["email_paciente"])
            startActivity(intent)
        }
    }

    fun fabClickedPacientes(v: View){
        val intent = Intent(this, lista_pacientes::class.java)
        startActivity(intent)
    }
    public override fun onResume() {
        super.onResume()
        loadIntoList()
    }


}