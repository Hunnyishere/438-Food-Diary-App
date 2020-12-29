package com.example.cse438.caloriecounter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ExerciseListAdapter(private var activity: Activity, private var items: ArrayList<String>, private var calories: ArrayList<Int>): BaseAdapter() {
//each ArrayList retrieve one column
    /**
     * Need to override the ViewHolder method
     */
    private class ViewHolder(row: View?){
        var exerciseName: TextView? = null
        var exerciseCalory: TextView? = null
        var buttonDelete: Button? = null

        init {
            this.exerciseName = row?.findViewById(R.id.exerciseName)  //found in add_exercise.xml
            this.exerciseCalory = row?.findViewById(R.id.exerciseCalory)
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
            view = inflater.inflate(R.layout.add_exercise, null)
            viewHolder = ViewHolder(view)  //display number+text
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val exercise = items[position]  //exercise
        val calory = calories[position]  //calory
        viewHolder.exerciseCalory?.text = calory.toString()
        viewHolder.exerciseName?.text = exercise
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