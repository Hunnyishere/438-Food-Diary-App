package com.example.cse438.caloriecounter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class FoodListAdapter(private var activity: Activity, private var items: ArrayList<String>, private var calories: ArrayList<Int>): BaseAdapter() {
//each ArrayList retrieve one column
    /**
     * Need to override the ViewHolder method
     */
    private class ViewHolder(row: View?){
        var foodName: TextView? = null
        var foodCalory: TextView? = null
        var buttonDelete: Button? = null

        init {
            this.foodName = row?.findViewById(R.id.foodName)  //found in add_food.xml
            this.foodCalory = row?.findViewById(R.id.foodCalory)
        }
    }

    /**
     * Displays the information in the row format we want
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // the position returns specific chosen item from the whole list
        val view: View?  //name: type
        val viewHolder: ViewHolder
        if (convertView == null) {  //hasn't been created
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.add_food, null)
            viewHolder = ViewHolder(view)  //display number+text
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val food = items[position]  //food
        val calory = calories[position]  //calory
        viewHolder.foodCalory?.text = calory.toString()
        viewHolder.foodName?.text = food
        //map list to viewHolder

        return view as View
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}