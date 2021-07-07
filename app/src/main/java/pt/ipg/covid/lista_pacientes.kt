package pt.ipg.covid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class lista_pacientes : AppCompatActivity() {
    private val dbHandler = BdCovidOpenHelper(this, null)
    lateinit var namePacienteEditText:EditText
    lateinit var agePacienteEditText:EditText
    lateinit var emailPacienteEditText:EditText
    lateinit var modifyIdPaciente:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacientes)

        namePacienteEditText = findViewById(R.id.name_paciente)
        agePacienteEditText = findViewById(R.id.age_paciente)
        emailPacienteEditText = findViewById(R.id.email_paciente)

        /* Check  if activity opened from List Item Click */
        if(intent.hasExtra("id_paciente")){
            modifyIdPaciente = intent.getStringExtra("id_paciente")!!
            namePacienteEditText.setText(intent.getStringExtra("name_paciente"))
            agePacienteEditText.setText(intent.getStringExtra("age_paciente"))
            emailPacienteEditText.setText(intent.getStringExtra("email_paciente"))
            findViewById<Button>(R.id.btnAdd_paciente).visibility = View.GONE
        }else{
            findViewById<Button>(R.id.btnUpdate_paciente).visibility = View.GONE
            findViewById<Button>(R.id.btnDelete_paciente).visibility = View.GONE
        }

    }


    fun add_paciente(v:View){
        val name_paciente = namePacienteEditText.text.toString()
        val age_paciente = agePacienteEditText.text.toString()
        val email_paciente = emailPacienteEditText.text.toString()
        dbHandler.insertRowPacientes(name_paciente , age_paciente, email_paciente)
        Toast.makeText(this, "Paciente Adicionado", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun update_paciente(v:View){
        val name_paciente = namePacienteEditText.text.toString()
        val age_paciente = agePacienteEditText.text.toString()
        val email_paciente = emailPacienteEditText.text.toString()
        dbHandler.updateRowPacientes(modifyIdPaciente, name_paciente, age_paciente, email_paciente)
        Toast.makeText(this, "Paciente Atualizado", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun delete_paciente(v:View){
        dbHandler.deleteRowPacientes(modifyIdPaciente)
        Toast.makeText(this, "Paciente Apagado", Toast.LENGTH_SHORT).show()
        finish()
    }
}