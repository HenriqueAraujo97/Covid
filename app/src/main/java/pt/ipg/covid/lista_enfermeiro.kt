package pt.ipg.covid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class lista_enfermeiro : AppCompatActivity() {
    private val dbHandler = BdCovidOpenHelper(this, null)
    lateinit var nameEditText:EditText
    lateinit var ageEditText:EditText
    lateinit var emailEditText:EditText
    lateinit var modifyId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_enfermeiro)

        nameEditText = findViewById(R.id.name)
        ageEditText = findViewById(R.id.number)
        emailEditText = findViewById(R.id.email)

        /* Check  if activity opened from List Item Click */
        if(intent.hasExtra("id")){
            modifyId = intent.getStringExtra("id")!!
            nameEditText.setText(intent.getStringExtra("name"))
            ageEditText.setText(intent.getStringExtra("age"))
            emailEditText.setText(intent.getStringExtra("email"))
            findViewById<Button>(R.id.btnAdd).visibility = View.GONE
        }else{
            findViewById<Button>(R.id.btnUpdate).visibility = View.GONE
            findViewById<Button>(R.id.btnDelete).visibility = View.GONE
        }

    }


    fun Add(v:View){
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()
        val email = emailEditText.text.toString()
        dbHandler.insertRow(name, age, email)
        Toast.makeText(this, "Enfermeiro(a) Adicionado(a)", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun update(v:View){
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()
        val email = emailEditText.text.toString()
        dbHandler.updateRow(modifyId, name, age, email)
        Toast.makeText(this, "Enfermeiro(a) Atualizado(a)", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun delete(v:View){
        dbHandler.deleteRow(modifyId)
        Toast.makeText(this, "Enfermeiro(a) Apagado(a)", Toast.LENGTH_SHORT).show()
        finish()
    }
}