package com.example.flashcardquizapp1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity(){
    lateinit var scoreText: TextView
    lateinit var resultMessage: TextView
    lateinit var reviewText: TextView
    lateinit var reviewButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        scoreText = findViewById(R.id.scoreText)
        resultMessage = findViewById(R.id.resultMessage)
        reviewText = findViewById(R.id.reviewText)
        reviewButton = findViewById(R.id.reviewButton)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        scoreText.text = getString(R.string.score_text, score, total)

        if (score >= 3) {
            resultMessage.text = getString(R.string.great_job)
        } else {
            resultMessage.text = getString(R.string.keep_practising)
        }

        reviewButton.setOnClickListener {
            reviewText.text = getString(R.string.review_text)
        }
    }
}