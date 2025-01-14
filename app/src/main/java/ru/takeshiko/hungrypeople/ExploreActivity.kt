package ru.takeshiko.hungrypeople

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ExploreActivity : AppCompatActivity() {

    /**
     * Called when the activity is created.
     * Initializes views and contains page fragments.
     *
     * @param savedInstanceState Bundle object that contains the activity's previous state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
    }
}