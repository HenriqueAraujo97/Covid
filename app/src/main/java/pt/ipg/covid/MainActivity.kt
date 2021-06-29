package pt.ipg.covid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun verPaciente(view: View){

        val intentPaciente = Intent(this,MostrarPaciente::class.java)

        startActivity(intentPaciente)

    }

    fun verEnfermeiro(view: View){

        val intentEnfermeiro = Intent(this,MostrarEnfermeiro::class.java)

        startActivity(intentEnfermeiro)

    }

    fun verVacina(view: View){

        val intentVacina = Intent(this,MostrarVacina::class.java)

        startActivity(intentVacina)

    }
}