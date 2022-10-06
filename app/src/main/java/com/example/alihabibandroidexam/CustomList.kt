package com.example.alihabibandroidexam
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*


class CustomList(
    private val context: Activity,
    private var foodNames: List<FoodItem>,
) :
    ArrayAdapter<FoodItem>(context, R.layout.row_item, foodNames), Filterable {

    private var mFoodNames: List<FoodItem> = foodNames
    override fun getCount(): Int {
        return mFoodNames.size
    }

    override fun getView(position: Int , convertView: View?, parent: ViewGroup): View {
        var row = convertView
        val inflater = context.layoutInflater
        if (convertView == null) row = inflater.inflate(R.layout.row_item, null, true)
        val textViewFood : TextView = row!!.findViewById<TextView>(R.id.textViewFood)

        val imageFood : ImageView = row!!.findViewById<ImageView>(R.id.imageViewFood)

        textViewFood.text = mFoodNames[position].name

        imageFood.setImageResource(mFoodNames[position].picture)

        return row
    }

    override fun getItem(position: Int): FoodItem? {

        return mFoodNames[position]

    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                val temp: List<FoodItem> = filterResults.values as List<FoodItem>
                mFoodNames = temp
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.lowercase()

                val filterResults = FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    foodNames
                else
                    foodNames.filter {
                        it.name.lowercase().contains(queryString)

                    }
                return filterResults
            }
        }
    }
}