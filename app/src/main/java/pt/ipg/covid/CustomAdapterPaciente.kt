package pt.ipg.covid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapterPaciente(private val context: Context,
                    private val dataList: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    private val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int { return dataList.size }
    override fun getItem(position: Int): Int { return position }
    override fun getItemId(position: Int): Long { return position.toLong() }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dataitem = dataList[position]

        val rowView = inflater.inflate(R.layout.list_row_pacientes, parent, false)
        rowView.findViewById<TextView>(R.id.row_name_paciente).text = dataitem["name_paciente"]
        rowView.findViewById<TextView>(R.id.row_age_paciente).text = "Age: " + dataitem["age_paciente"]
        rowView.findViewById<TextView>(R.id.row_email_paciente).text = "Email: " + dataitem["email_paciente"]



        rowView.tag = position
        return rowView
    }
}