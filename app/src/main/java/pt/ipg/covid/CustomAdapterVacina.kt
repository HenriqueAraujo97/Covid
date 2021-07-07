package pt.ipg.covid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapterVacina(private val context: Context,
                            private val dataList: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    private val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int { return dataList.size }
    override fun getItem(position: Int): Int { return position }
    override fun getItemId(position: Int): Long { return position.toLong() }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dataitem = dataList[position]

        val rowView = inflater.inflate(R.layout.list_row_vacinas, parent, false)
        rowView.findViewById<TextView>(R.id.row_name_vacina).text = dataitem["name_vacina"]
        rowView.findViewById<TextView>(R.id.row_nr_vacina).text = "Numero: " + dataitem["nr_vacina"]
        rowView.findViewById<TextView>(R.id.row_validade_vacina).text = "Validade: " + dataitem["validade_vacina"]



        rowView.tag = position
        return rowView
    }
}