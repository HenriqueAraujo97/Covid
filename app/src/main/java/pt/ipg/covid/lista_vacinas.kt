package pt.ipg.covid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class lista_vacinas : AppCompatActivity() {
    private val dbHandler = BdCovidOpenHelper(this, null)
    lateinit var nameVacinaEditText: EditText
    lateinit var NrVacinaEditText: EditText
    lateinit var ValidadeVacinaEditText: EditText
    lateinit var modifyIdVacina:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_vacinas)

        nameVacinaEditText = findViewById(R.id.name_vacina)
        NrVacinaEditText = findViewById(R.id.nr_vacina)
        ValidadeVacinaEditText = findViewById(R.id.validade_vacina)

        /* Check  if activity opened from List Item Click */
        if(intent.hasExtra("id_vacina")){
            modifyIdVacina = intent.getStringExtra("id_vacina")!!
            nameVacinaEditText.setText(intent.getStringExtra("name_vacina"))
            NrVacinaEditText.setText(intent.getStringExtra("nr_vacina"))
            ValidadeVacinaEditText.setText(intent.getStringExtra("validade_vacina"))
            findViewById<Button>(R.id.btnAdd_vacina).visibility = View.GONE
        }else{
            findViewById<Button>(R.id.btnUpdate_vacina).visibility = View.GONE
            findViewById<Button>(R.id.btnDelete_vacina).visibility = View.GONE
        }

    }


    fun add_vacina(v: View){
        val name_vacina = nameVacinaEditText.text.toString()
        val nr_vacina = NrVacinaEditText.text.toString()
        val validade_vacina = ValidadeVacinaEditText.text.toString()
        dbHandler.insertRowVacinas(name_vacina , nr_vacina, validade_vacina)
        Toast.makeText(this, "Vacina Adicionada", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun update_vacina(v: View){
        val name_vacina = nameVacinaEditText.text.toString()
        val nr_vacina = NrVacinaEditText.text.toString()
        val validade_vacina = ValidadeVacinaEditText.text.toString()
        dbHandler.updateRowVacina(modifyIdVacina, name_vacina, nr_vacina, validade_vacina)
        Toast.makeText(this, "Vacina Atualizada", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun delete_vacina(v: View){
        dbHandler.deleteRowVacinas(modifyIdVacina)
        Toast.makeText(this, "Vacina Apagada", Toast.LENGTH_SHORT).show()
        finish()
    }
}