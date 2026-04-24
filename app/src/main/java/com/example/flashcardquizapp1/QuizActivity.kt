package com.example.flashcardquizapp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.activity.enableEdgeToEdge

class QuizActivity : AppCompatActivity() {

    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
    lateinit var hackButton: Button
    lateinit var mythButton: Button
    lateinit var nextButton: Button

    var currentQuestion = 0
    var score = 0
    var answered = false

    val questions = arrayOf(
        "Putting your phone in rice fixes water damage",
        "Drinking water improves focus",
        "Cracking knuckles causes arthritis",
        "Using dark mode saves battery life"
    )

    val correctAnswers = arrayOf(
        false, true, false, true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)
        hackButton = findViewById(R.id.hackButton)
        mythButton = findViewById(R.id.mythButton)
        nextButton = findViewById(R.id.nextButton)

        // Show first question
        questionText.text = questions[currentQuestion]

        hackButton.setOnClickListener {
            checkAnswer(true)
        }

        mythButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {

            if (!answered) {
                Toast.makeText(this, "Please answer first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentQuestion++

            if (currentQuestion < questions.size) {
                questionText.text = questions[currentQuestion]
                feedbackText.text = ""
                answered = false
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    fun checkAnswer(userAnswer: Boolean) {

        if (answered) return

        if (userAnswer == correctAnswers[currentQuestion]) {
            feedbackText.text = getString(R.string.correct)
            score++
        } else {
            feedbackText.text = getString(R.string.wrong)
        }

        Log.d("QuizApp", "Answer: $userAnswer")
        Log.d("QuizApp", "Score: $score")

        answered = true
    }
}



