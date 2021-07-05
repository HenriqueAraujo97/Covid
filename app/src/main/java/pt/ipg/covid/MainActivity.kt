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

        val intentPaciente = Intent(this,activity_paciente::class.java)

        startActivity(intentPaciente)

    }

    fun verEnfermeiro(view: View){

        val intentPaciente = Intent(this,enfermeiroLayout::class.java)

        startActivity(intentPaciente)

    }


}