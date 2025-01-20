package ru.takeshiko.hungrypeople.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.clients.SupabaseClient
import ru.takeshiko.hungrypeople.models.ContactMessage
import ru.takeshiko.hungrypeople.ui.animators.ViewClickAnimator

/**
 * ContactFragment is a fragment that provides a contact form for users to send messages.
 * The form includes fields for name, email, phone, and a message.
 * A send button is available for submitting the message, with a click animation provided by {@link ViewClickAnimator}.
 * This fragment also validates user input and sends the message to the server via {@link SupabaseClient}.
 */
class ContactFragment : Fragment(R.layout.fragment_contact) {

    // Views for user input and interaction
    private lateinit var nameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var contactMessageField: EditText
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

        // Initialize fields
        nameField = view.findViewById(R.id.name_field)
        emailField = view.findViewById(R.id.email_field)
        phoneField = view.findViewById(R.id.phone_field)
        contactMessageField = view.findViewById(R.id.contact_message_field)
        btnSendMessage = view.findViewById(R.id.btn_send_message)

        // Create an animator for button click animations
        val animator = ViewClickAnimator()

        // Set listener for the send button to validate input and send contact message
        btnSendMessage.setOnClickListener {
            animator.animate(btnSendMessage) {
                if (validateInput()) {
                    lifecycleScope.launch {
                        sendMessage()
                    }
                }
            }
        }
    }

    /**
     * Validates the user input from the form fields.
     * Ensures the name, phone number, email, and message fields are correctly formatted.
     *
     * @return `true` if all inputs are valid, `false` otherwise.
     */
    private fun validateInput(): Boolean {
        val name = nameField.text.toString().trim()
        if (name.isNotEmpty() && !name.matches(NameRegex) || name.isEmpty()) {
            showToast("Enter the correct name!")
            return false
        }

        val phone = phoneField.text.toString().trim()
        if (phone.isNotEmpty() && !phone.matches(PhoneRegex) || phone.isEmpty()) {
            showToast("Enter the correct phone number!")
            return false
        }

        val email = emailField.text.toString().trim()
        if (email.isNotEmpty() && !email.matches(EmailRegex) || email.isEmpty()) {
            showToast("Enter a valid email!")
            return false
        }

        val contactMessage = contactMessageField.text.toString().trim()
        if (contactMessage.isNotEmpty()) {
            showToast("Enter a message!")
            return false
        }

        return true
    }

    /**
     * Sends the user's contact message to the server using SupabaseClient.
     * Displays a success message on successful submission, or an error message if the operation fails.
     */
    private fun sendMessage() {
        val name = nameField.text.toString()
        val email = emailField.text.toString()
        val phone = phoneField.text.toString()
        val message = contactMessageField.text.toString()

        val contactMessage = ContactMessage(
            name = name,
            email = email,
            phone = phone,
            message = message
        )

        lifecycleScope.launch {
            try {
                SupabaseClient.addContactMessage(contactMessage)
                showToast("Reservation successful!")
            } catch (e: Exception) {
                showToast("Error saving contact message!")
                e.printStackTrace()
            }
        }
    }

    /**
     * Shows a toast message to the user.
     *
     * @param message The message to display.
     */
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val NameRegex = """^[a-zA-Zа-яА-Я ]+$""".toRegex()
        private val PhoneRegex = """\+?\d{10,15}""".toRegex()
        private val EmailRegex = """^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
    }
}