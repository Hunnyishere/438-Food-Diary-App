package com.example.cse438.caloriecounter

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calory_alert.*
import kotlinx.android.synthetic.main.enter_calory.*
import kotlinx.android.synthetic.main.enter_calory.view.*

class MainActivity : AppCompatActivity() {

    // List of foods and calories the user enters
    private var listOfFoods = ArrayList<String>()
    private var listOfCalories = ArrayList<Int>()
    private var listOfExercises = ArrayList<String>()
    private var listOfExerciseCalories = ArrayList<Int>()

    private var oriTotalCalory: Int = 0

    // Text and List views
    private var foodListView: ListView? = null
    private var exerciseListView: ListView? = null


    private var totalCalories = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup functions
        getFoodInitial()
        getExerciseInitial()
        dialogView()
    }


    /**
     *  Assigns the food list and text view objects with their respective views
     */
    private fun getFoodInitial() {
        foodListView = foods
//        refer to listview id:tasks from activity_main.xml

        // Setting up the adapter using our custom built adapter
        val adapter = FoodListAdapter(this, listOfFoods, listOfCalories)
        foodListView?.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    /**
     *  Assigns the exercise list and text view objects with their respective views
     */
    private fun getExerciseInitial() {
        exerciseListView = exercises
//        refer to listview id:tasks from activity_main.xml

        // Setting up the adapter using our custom built adapter
        val adapter = ExerciseListAdapter(this, listOfExercises, listOfExerciseCalories)
        exerciseListView?.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    /**
     * Displays the dialog box asking the user for a name
     */
    private fun dialogView() {
        // Opens the dialog view asking the user for
        val dialogView = LayoutInflater.from(this).inflate(R.layout.enter_calory, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Enter Total Calories")
        val mAlertDialog = mBuilder.show()

        // Sets an onclick listener on the dialog box button
        mAlertDialog.submitTotalCaloryButton.setOnClickListener {
            val totalCalories = dialogView.totalCalories.text.toString()
            // If the string is empty, we do not want to accept that as an input
            if(totalCalories!="" && totalCalories.toInt()>= 0 ){
                oriTotalCalory = totalCalories.toInt()
                caloryRemainingNumber.text = totalCalories
                mAlertDialog.dismiss()
            }
            else{
                val myToast = Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }


//    fun deleteFood(view: View?){
//        //event upon pressing deleteButton
//
//        listView?.setOnItemClickListener{parent,view,position,id->
//            Toast.makeText(this, "Deleting food", Toast.LENGTH_SHORT).show()
//            //Toast.makeText(this, "Clicked item :"+" "+position,Toast.LENGTH_SHORT).show()
//            listOfFoods.removeAt(position)
//            listOfCalories.remove(position)
//        }
//        (listView?.adapter as? FoodListAdapter)?.notifyDataSetChanged()
//    }

    fun resetAll(view: View?){
        var spentCalory: Int = 0
        var caloryRemainingView: TextView = findViewById(R.id.caloryRemainingNumber)

        caloryRemainingView.text = oriTotalCalory.toString()
        caloryRemainingView.setTextColor(android.graphics.Color.BLACK)
        caloryTotal.text = "0"
        totalCalories = 0

        listOfFoods.clear()
        listOfCalories.clear()
        listOfExercises.clear()
        listOfExerciseCalories.clear()

        (foodListView?.adapter as? FoodListAdapter)?.notifyDataSetChanged()
        (exerciseListView?.adapter as? ExerciseListAdapter)?.notifyDataSetChanged()
    }

    /**
     * Handler for adding a new food
     */
    fun addFood(view: View?){
        val foodToAdd = newFood.text.toString()
        val caloryToAdd = newCalory.text.toString()
        var caloryRemainingView: TextView = findViewById(R.id.caloryRemainingNumber)
        var remainCalory: Int = caloryRemainingView.text.toString().toInt()
        if(foodToAdd == "" || caloryToAdd==""){
            val myToast = Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else if(caloryToAdd.toInt()<=0){
            val myToast = Toast.makeText(this, "Calory should be greater than 0", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else{
            var caloryToAddNumber = caloryToAdd.toInt()
            listOfFoods.add(foodToAdd)
            listOfCalories.add(caloryToAddNumber)
            totalCalories += caloryToAddNumber
            caloryTotal.text = totalCalories.toString()
            remainCalory -= caloryToAddNumber
            caloryRemainingView.text = remainCalory.toString()
            if(remainCalory < 0){
                caloryRemainingView.setTextColor(android.graphics.Color.RED)
            }
            else if(remainCalory <= 100){
                caloryRemainingView.setTextColor(android.graphics.Color.BLACK)
            }
            if(remainCalory <= 0){
                caloryExceedAlert()
            }
            (foodListView?.adapter as? FoodListAdapter)?.notifyDataSetChanged()
        }
    }

    /**
     * Handler for adding a new exercise
     */
    fun addExercise(view: View?){
        val exerciseToAdd = newExercise.text.toString()
        val exerciseCaloryToSubtract = newExerciseCalory.text.toString()
        var caloryRemainingView: TextView = findViewById(R.id.caloryRemainingNumber)
        var remainCalory: Int = caloryRemainingView.text.toString().toInt()
        if(exerciseToAdd == "" || exerciseCaloryToSubtract==""){
            val myToast = Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else if(exerciseCaloryToSubtract.toInt()<=0){
            val myToast = Toast.makeText(this, "Calory should be greater than 0", Toast.LENGTH_SHORT)
            myToast.show()
        }
        else{
            var caloryToSuctractNumber = exerciseCaloryToSubtract.toInt()
            listOfExercises.add(exerciseToAdd)
            listOfExerciseCalories.add(caloryToSuctractNumber)
            totalCalories -= caloryToSuctractNumber
            caloryTotal.text = totalCalories.toString()
            remainCalory += caloryToSuctractNumber
            caloryRemainingView.text = remainCalory.toString()
            if(remainCalory >= 0){
                caloryRemainingView.setTextColor(android.graphics.Color.BLACK)
            }
            if(remainCalory > oriTotalCalory){
                tooMuchExerciseAlert()
                caloryRemainingView.setTextColor(android.graphics.Color.GREEN)
            }
            (exerciseListView?.adapter as? ExerciseListAdapter)?.notifyDataSetChanged()
        }
    }


    private fun caloryExceedAlert() {
        // Opens the dialog view that alerts the user calory amount exceeds
        val dialogView = LayoutInflater.from(this).inflate(R.layout.calory_alert, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("You have already met your expected calorie intake! No more intake!")
        val mAlertDialog = mBuilder.show()

        // Sets an onclick listener on the dialog box button
        mAlertDialog.closeAlertButton.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }


    private fun tooMuchExerciseAlert() {
        // Opens the dialog view that alerts the user calory amount exceeds
        val dialogView = LayoutInflater.from(this).inflate(R.layout.too_much_exercise_alert, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Your calory outlet is greater than intake! Have a rest :-)")
        val mAlertDialog = mBuilder.show()

        // Sets an onclick listener on the dialog box button
        mAlertDialog.closeAlertButton.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }


}
