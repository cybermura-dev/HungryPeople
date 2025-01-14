package ru.takeshiko.hungrypeople.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.ui.animators.ViewClickAnimator

/**
 * ContactFragment is a fragment that provides a contact form for users to send messages.
 * It includes a button for sending messages and uses a {@link ViewClickAnimator} for button click animations.
 */
class ContactFragment : Fragment(R.layout.fragment_contact) {

    private lateinit var btnSendMessage: Button

    /**
     * Called when the fragment's view is created.
     * Initializes the send button and sets up a click listener with an animation.
     *
     * @param view The root view of the fragment.
     * @param savedInstanceState Bundle object that contains the fragment's previous state, if any.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSendMessage = view.findViewById(R.id.btn_send_message)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        // Set listener for the send button to validate input and check reservation availability
        btnSendMessage.setOnClickListener {
            animator.animate(btnSendMessage) {
                // TODO
            }
        }
    }
}