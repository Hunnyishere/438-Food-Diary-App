In this file you should include:

Any information you think we should know about your submission
* Is there anything that doesn't work? Why?
* Is there anything that you did that you feel might be unclear? Explain it here.



A description of the creative portion of the assignment
* Describe your feature
(1) The calory input for either food or exercise is <=0, prompt a hint at the bottom that user need to input calory > 0.
(2) When adding one food makes "calories remaining" <= 0, an alert will pop up to hint the user don't intake calories any more.
When adding one exercise makes "calories remaining" >= initial total calory setting, an alert will pop up to hint the user need a rest, the text color also becomes green.
When either of the events makes "calories remaining" within the range [0,100], it resets the color to black.
(3) User can clear all records and reset "calories remaining" and "total calories" to their original values.
(4) User can add exercise on this app, which consumes calories.

* Why did you choose this feature?
(1) To avoid wrong inputs from user. And save display space from meaningless inputs.
(2) Only red text colors might not be catchy enough to warn users, a pop-up alert has better effects.
(3) User might add wrong inputs, or start a new day, we help them reset everything and start over again.
(4) Users who intake calories can also consume calories. So adding exercise can help users better manage their calories balance.

* How did you implement it?
(1) Add a Toast in addFood() and addExercise function to check if calory-to-add is <=0.
(2) Create functions caloryExceedAlert() and tooMuchExerciseAlert() which open a dialog view with hints and a close button. 
The functions are called in addFood() and addExercise() when checking "calories remaining".
(3) Create a function resetAll() in MainActivity.kt, reset the display values of "calories remaining" and "total calories",
and clear all the arraylists recording food, exercise and calories.
(4) Mimic the realization of addFood, add layout (2 listviews and a wrapped LinearLayout), activity, adapter files of addExercise accordingly.
The major difference is when adding an exercise, the calory is reduced.

  1. (5/5 Points) User can enter total calorie amount on start up 
2. (5/5 Points) User can add new food item by name 
3. (5/5 Points) User can add new food item by calorie 
4. (5/5 Points) Calories remaining is updated with each new food item 
5. (5/5 Points) Calorie consumed is updated with each new food item
 6. (5/5 Points) The list of food items displays foods and their respective calories amounts 
7. (3/3 Points) Color change when calorie count becomes negative 
8. (5/5 Points) All inputs are filtered and error messages are displayed accordingly 
9. (4/4 points) The design and implementation of the User Interface is clean 
10. (8/8 Points) Creative portion - design your own feature(s)! 

Nice job! I like your creative features. I think that having two lists for the different items is very nice.

Well done!

Total: 50 / 50