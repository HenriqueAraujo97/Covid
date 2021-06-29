package pt.ipg.covid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MostrarPaciente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.pacientes)

    }

    fun guardarPacientes(view: View){
        val editTextName = findViewById<EditText>(R.id.editTextTextName)
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val editTextNumero = findViewById<EditText>(R.id.editTextNumero)

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
            val intent = Intent(this, MostrarPaciente::class.java).apply {
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