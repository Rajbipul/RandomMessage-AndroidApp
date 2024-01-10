package com.example.myapplication

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.log

// Main Activity class extends from AppCompactActivity giving it more functionality.
class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var message: TextView
    // here you promise to the class that you will later define it. Define it in OnCreate

    //onCreate is a lifecycle method that runs only once at the start of the app.
    override fun onCreate(savedInstanceState: Bundle?) {
        //extends onCreate rather than completely overriding it.
        super.onCreate(savedInstanceState)

        // This process is called layout-inflation. This is the process by which the views that we define in activity_main file are converted into kotlin objects.
        setContentView(R.layout.activity_main)

        // Get a reference to the button view from our layout and a click listener.
        //updateButton name and Button type. find the update button in main.
        val updateButton : Button = findViewById(R.id.main_bt_update)
        //updateButton.setOnClickListener { Log.i("test", "you have pressed the update message") }
        updateButton.setOnClickListener { updatemessage()}

        // Setting the values to our declared views.
        name = findViewById(R.id.main_activity_et_name)
        message = findViewById(R.id.main_activity_message)
    }

    //Code to take text from the input and display a new message.
    private fun updatemessage(){
        //this is a bad practice (resource intensive. Take it to the upper class so that it does not need to find the text again and again.
        //val name:EditText=findViewById(R.id.main_activity_et_name)
        //val message: TextView=findViewById(R.id.main_activity_message)
        //this can also be written as ...
        //val message =findViewById<TextView>(R.id.main_activity_message)

            // Noe get the username from the edit text view.
        // here the username is going to be editable.
        val username = name.text
        //display the message on app.
        //message.text = "Hello $username ! Nice to meet you."

        // Print a random message on the display.
        val randomMessage = listOf("Never Give Up", "Never look Back", "Believe your self", "Beat the limit", "You are the best")
        val index = (0..4).random() //randomly select the number
        val currentMessage = randomMessage[index]

        // Never leave the Space black message. Error...
        if(username.toString()==""){
            message.text = "Please enter the name."
        }else{
            message.text = "Hello $username ! Nice to meet you. $currentMessage"
        }

        // Now clear the editText. Use SetText bcz editText require a editable.
        name.setText("")

        // Hide the keyboard.
        hideKeyboard()
    }

    // Function to Hide the keyboard after pressing enter. Using InputMethodManager.
    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(name.windowToken, 0)
    }
}