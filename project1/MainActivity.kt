package com.example.project1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import org.w3c.dom.Text

var wordToGuess = ""
var guessNum = 1

class MainActivity : AppCompatActivity() {
    lateinit var check: TextView
    lateinit var resetbtn: Button
    lateinit var playAgain: Button
    lateinit var submit: Button
    lateinit var finalAnswer: TextView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        {
            v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        check = findViewById(R.id.check)
        resetbtn = findViewById(R.id.reset)
        playAgain = findViewById(R.id.runItBack)
        submit = findViewById(R.id.submitGuess)
        finalAnswer = findViewById(R.id.finalAnswer)
        editText = findViewById(R.id.guess)

        playAgain.visibility = View.GONE
        finalAnswer.visibility = View.GONE



        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        Log.d("Worlde","$wordToGuess")

        resetbtn.setOnClickListener { reset() }


        playAgain.setOnClickListener { reset() }

        submit.setOnClickListener {


            var guessedWord = editText.text.toString().trim().uppercase()
            if (guessedWord.length != 4) return@setOnClickListener
            var results = checkGuess(guessedWord)
            check.append("Guess #$guessNum Check $guessedWord: $results\n")

            if (results == "OOOO")
            {
                finalAnswer.text = wordToGuess
                finalAnswer.visibility = View.VISIBLE
                editText.visibility = View.GONE
                playAgain.visibility = View.VISIBLE
                return@setOnClickListener
            }

            editText.text.clear()
            guessNum++

            if (guessNum > 3){
                finalAnswer.text = wordToGuess
                finalAnswer.visibility = View.VISIBLE
                editText.visibility = View.GONE
                playAgain.visibility = View.VISIBLE
                return@setOnClickListener
            }
        }




    }
    private fun reset()
    {
        check.text=""
        playAgain.visibility = View.GONE
        finalAnswer.visibility = View.GONE
        editText.visibility = View.VISIBLE
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        guessNum  = 1
        editText.text.clear()
        Log.d("Worlde","$wordToGuess")
    }
}

private fun checkGuess(guess: String) : String {
    var result = ""
    for (i in 0..3) {
        if (guess[i] == wordToGuess[i]) {
            result += "O"
        }
        else if (guess[i] in wordToGuess) {
            result += "+"
        }
        else {
            result += "X"
        }
    }
    return result
}

