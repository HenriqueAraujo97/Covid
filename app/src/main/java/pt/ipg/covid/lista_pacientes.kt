package pt.ipg.covid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView


class lista_pacientes() : AppCompatActivity() {
    var nome: String? = null
    var data: String? = null
    var numero: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacientes)


         nome = intent.getStringExtra(activity_paciente.INFO_EXTRA_NOME)
         data = intent.getStringExtra(activity_paciente.INFO_EXTRA_DATA)
         numero = intent.getStringExtra(activity_paciente.INFO_EXTRA_NUMERO)

        findViewById<TextView>(R.id.textViewNome).text = nome
        findViewById<TextView>(R.id.textViewNascimento).text = data
        findViewById<TextView>(R.id.textViewNumero).text = numero



    }
    fun editarpaciente(view: View){

        finish()

    }

}