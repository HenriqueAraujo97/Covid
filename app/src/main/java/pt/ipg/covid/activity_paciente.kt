package pt.ipg.covid

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class activity_paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)
    }
    fun guardarPacientes(view: View){



        val editTextName = findViewById<EditText>(R.id.editTextTextPaciente)
        val editTextDate = findViewById<EditText>(R.id.editTextDatePaciente)
        val editTextNumero = findViewById<EditText>(R.id.editTextNumberPaciente)

        val nome = editTextName.text.toString()
        val data = editTextDate.text.toString()
        val numero = editTextNumero.text.toString()

        var dadosCorretos = true

        if (nome.isBlank()) {
            editTextName.error = getString(R.string.nome_obrigatorio)
            dadosCorretos = false
        }

        if (data.isBlank()) {
            editTextDate.error = getString(R.string.data_obrigatorio)
            dadosCorretos = false
        }

        if (numero.isBlank() && numero.length > 9) {
            editTextNumero.error = getString(R.string.telefone_obrigatorio)
            dadosCorretos = false
        }


        if (dadosCorretos) {
            val intent = Intent(this, lista_pacientes::class.java).apply {
                putExtra(INFO_EXTRA_NOME, nome)
                putExtra(INFO_EXTRA_DATA, data)
                putExtra(INFO_EXTRA_NUMERO, numero)

            }

           startActivity(intent)
        }
    }


    companion object{
        const val INFO_EXTRA_NOME = "NOME"
        const val INFO_EXTRA_DATA = "DATA"
        const val INFO_EXTRA_NUMERO = "NUMERO"


    }

}
