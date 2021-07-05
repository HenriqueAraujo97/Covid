package pt.ipg.covid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class enfermeiroLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enfermeiro_layout)
    }
    fun guardarEnfermeiros(view: View){



        val editTextName = findViewById<EditText>(R.id.editTextTextEnfermeiro)
        val editTextNumero = findViewById<EditText>(R.id.editTextNumberEnfermeiro)

        val nome = editTextName.text.toString()
        val numero = editTextNumero.text.toString()

        var dadosCorretos = true

        if (nome.isBlank()) {
            editTextName.error = getString(R.string.nome_obrigatorio)
            dadosCorretos = false
        }


        if (numero.isBlank() && numero.length > 9) {
            editTextNumero.error = getString(R.string.telefone_obrigatorio)
            dadosCorretos = false
        }


        if (dadosCorretos) {
            val intent = Intent(this, lista_enfermeiro::class.java).apply {
                putExtra(INFO_EXTRA_NOME, nome)
                putExtra(INFO_EXTRA_NUMERO, numero)

            }

            startActivity(intent)
        }
    }


    companion object{
        const val INFO_EXTRA_NOME = "NOME"
        const val INFO_EXTRA_NUMERO = "NUMERO"


    }
}