package com.example.stopwatch

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var holdButton: Button
    private lateinit var timer: CountDownTimer
    private lateinit var timeDisplay: TextView

    private var isRunning = false
    private var timeElapsed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timeDisplay = findViewById(R.id.timeTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        holdButton = findViewById(R.id.holdButton)

        startButton.setOnClickListener {
            if (!isRunning) {
                startTimer()
                isRunning = true
            }
        }

        stopButton.setOnClickListener {
            if (isRunning) {
                stopTimer()
                isRunning = false
            }
        }

        holdButton.setOnClickListener {
            if (isRunning) {
                pauseTimer()
                isRunning = false
            } else {
                resumeTimer()
                isRunning = true
            }
        }

        timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (isRunning) {
                    timeElapsed += 1000
                    updateTimeDisplay()
                }
            }

            override fun onFinish() {

            }
        }
        updateTimeDisplay()
    }

    private fun startTimer() {
        timer.start()
    }

    private fun stopTimer() {
        timer.cancel()
        val formattedTime = String.format("%02d:%02d", 0, 0)
        timeDisplay.text = formattedTime
        timeElapsed = 0

    }

    private fun pauseTimer() {
        timer.cancel()
    }

    private fun resumeTimer() {
        timer.start()
    }
    private fun updateTimeDisplay() {
        val minutes = (timeElapsed / 1000 / 60).toInt()
        val seconds = (timeElapsed / 1000 % 60).toInt()
        val formattedTime = String.format("%02d:%02d", minutes, seconds)
        timeDisplay.text = formattedTime
    }


}
