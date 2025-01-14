package ru.takeshiko.hungrypeople.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.ui.animators.ViewClickAnimator

/**
 * HomeActivity class is the entry point of the application.
 * It manages the main screen with buttons for booking a table, exploring, and social media links.
 * This activity contains click listeners for interacting with the buttons and opening corresponding activities or URLs.
 */
class HomeActivity : AppCompatActivity() {

    /**
     * Called when the activity is created.
     * Initializes the views and sets click listeners for buttons.
     * This method also initializes a {@link ViewClickAnimator} to provide button click animations.
     *
     * @param savedInstanceState Bundle object that contains the activity's previous state, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize buttons
        val btnBookTable = findViewById<View>(R.id.btn_book_table)
        val btnExplore = findViewById<View>(R.id.btn_explore)
        val btnFacebook = findViewById<View>(R.id.logo_social_facebook)
        val btnInstagram = findViewById<View>(R.id.logo_social_instagram)
        val btnTwitter = findViewById<View>(R.id.logo_social_twitter)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        /**
         * Sets the click listener for the "Book Table" button.
         * When clicked, it navigates to the BookTableActivity.
         */
        btnBookTable.setOnClickListener {
            animator.animate(btnBookTable, onAnimationEnd = {
                val intent = Intent(this, BookTableActivity::class.java)
                startActivity(intent)
            })
        }

        /**
         * Sets the click listener for the "Explore" button.
         * When clicked, it navigates to the {@link ExploreActivity}.
         */
        btnExplore.setOnClickListener {
            animator.animate(btnExplore, onAnimationEnd = {
                val intent = Intent(this, ExploreActivity::class.java)
                startActivity(intent)
            })
        }

        /**
         * Sets the click listener for the Facebook logo.
         * When clicked, it opens the Facebook URL in a browser.
         */
        btnFacebook.setOnClickListener {
            animator.animate(btnFacebook, onAnimationEnd = {
                openUrl("https://www.facebook.com/")
            })
        }

        /**
         * Sets the click listener for the Instagram logo.
         * When clicked, it opens the Instagram URL in a browser.
         */
        btnInstagram.setOnClickListener {
            animator.animate(btnInstagram, onAnimationEnd = {
                openUrl("https://www.instagram.com/")
            })
        }

        /**
         * Sets the click listener for the Twitter logo.
         * When clicked, it opens the Twitter URL in a browser.
         */
        btnTwitter.setOnClickListener {
            animator.animate(btnTwitter, onAnimationEnd = {
                openUrl("https://twitter.com/")
            })
        }
    }

    /**
     * Opens the given URL in a browser using an intent.
     * This method creates an {@link Intent} with the {@link Intent#ACTION_VIEW} action
     * and starts the activity to open the URL.
     *
     * @param url The URL to be opened.
     */
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}