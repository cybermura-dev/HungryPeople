package ru.takeshiko.hungrypeople

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.takeshiko.hungrypeople.clients.SupabaseClient
import ru.takeshiko.hungrypeople.models.Reservation
import ru.takeshiko.hungrypeople.ui.ViewClickAnimator
import java.text.SimpleDateFormat
import java.util.*

/**
 * Activity for booking a table in a restaurant. This activity allows users to provide their details
 * and choose a date and time for the reservation.
 */
class BookTableActivity : AppCompatActivity() {

    // Views for user input and interaction
    private lateinit var nameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var peopleField: EditText
    private lateinit var dateField: TextView
    private lateinit var timeField: TextView
    private lateinit var btnSend: Button

    // Date and time formats
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    /**
     * Called when the activity is created.
     * Initializes the views and sets up listeners for buttons to select date, time, and submit the reservation.
     *
     * @param savedInstanceState Bundle containing the activity's previous state, if available.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        // Initialize fields
        nameField = findViewById(R.id.name_field)
        emailField = findViewById(R.id.email_field)
        phoneField = findViewById(R.id.phone_field)
        peopleField = findViewById(R.id.people_field)
        dateField = findViewById(R.id.date_field)
        timeField = findViewById(R.id.time_field)
        btnSend = findViewById(R.id.btn_send)

        // Set click listeners for date and time pickers
        dateField.setOnClickListener { showDatePickerDialog() }
        timeField.setOnClickListener { showTimePickerDialog() }

        val animator = ViewClickAnimator()

        // Set listener for the send button to validate input and check reservation availability
        btnSend.setOnClickListener {
            animator.animate(btnSend) {
                if (validateInput()) {
                    checkReservation()
                }
            }
        }
    }

    /**
     * Validates the user's input in the booking form.
     * Ensures all required fields are filled out correctly.
     *
     * @return true if all input fields are valid, false otherwise.
     */
    private fun validateInput(): Boolean {
        val name = nameField.text.toString().trim()
        if (name.isEmpty() || !name.matches(NameRegex)) {
            showToast("Enter the correct name!")
            return false
        }

        val people = peopleField.text.toString().trim()
        if (people.isEmpty() || !people.matches(PeopleRegex)) {
            showToast("Enter the number of people!")
            return false
        }

        if (dateField.text.isEmpty()) {
            showToast("Select a date!")
            return false
        }

        if (timeField.text.isEmpty()) {
            showToast("Select a time!")
            return false
        }

        val phone = phoneField.text.toString().trim()
        if (phone.isNotEmpty() && !phone.matches(PhoneRegex)) {
            showToast("Enter the correct phone number!")
            return false
        }

        val email = emailField.text.toString().trim()
        if (email.isNotEmpty() && !email.matches(EmailRegex)) {
            showToast("Enter a valid email!")
            return false
        }

        return true
    }

    /**
     * Checks if the restaurant is open at the selected date and time.
     * The restaurant has specific open hours for each day of the week.
     *
     * @param date The selected date for the reservation.
     * @param time The selected time for the reservation.
     * @return true if the restaurant is open, false otherwise.
     */
    private fun isRestaurantOpen(date: Date, time: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        calendar.time = time
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (dayOfWeek) {
            Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY -> {
                hour in 20..21
            }
            Calendar.SATURDAY -> {
                hour in 20..23 || hour in 0..2
            }
            Calendar.SUNDAY -> {
                hour in 0..2 || hour in 20..23
            }
            else -> false
        }
    }

    /**
     * Checks the reservation details and ensures that the restaurant is open at the selected time.
     * If everything is valid, it proceeds to save the reservation.
     */
    private fun checkReservation() {
        val selectedDateStr = dateField.text.toString()
        val selectedTimeStr = timeField.text.toString()

        val selectedDate = parseDate(selectedDateStr, dateFormat)
            ?: run {
                showToast("Invalid date format!")
                return
            }

        val selectedTime = parseDate(selectedTimeStr, timeFormat)
            ?: run {
                showToast("Invalid time format!")
                return
            }

        if (!isRestaurantOpen(selectedDate, selectedTime)) {
            showToast("The restaurant is closed at this time!")
            return
        }

        lifecycleScope.launch {
            saveReservation(selectedDateStr, selectedTimeStr)
        }
    }

    /**
     * Saves the reservation by sending the details to the Supabase server.
     *
     * @param selectedDate The selected date for the reservation.
     * @param selectedTime The selected time for the reservation.
     */
    private fun saveReservation(selectedDate: String, selectedTime: String) {
        val name = nameField.text.toString()
        val email = emailField.text.toString()
        val phone = phoneField.text.toString()
        val people = peopleField.text.toString().toInt()

        val reservation = Reservation(
            name,
            email,
            phone,
            people,
            selectedDate,
            selectedTime
        )

        lifecycleScope.launch {
            try {
                SupabaseClient.addReservation(reservation)
                showToast("Reservation successful!")
            } catch (e: Exception) {
                showToast("Error saving reservation: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    /**
     * Opens a date picker dialog for the user to select a reservation date.
     */
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance().apply {
                set(selectedYear, selectedMonth, selectedDay)
            }
            dateField.text = dateFormat.format(selectedDate.time)
        }, year, month, day).apply {
            datePicker.minDate = calendar.timeInMillis
            show()
        }
    }

    /**
     * Opens a time picker dialog for the user to select a reservation time.
     */
    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val selectedTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, selectedHour)
                set(Calendar.MINUTE, selectedMinute)
            }
            timeField.text = timeFormat.format(selectedTime.time)
        }, hour, minute, true).show()
    }

    /**
     * Parses a date string into a Date object using the specified format.
     *
     * @param dateStr The date string to parse.
     * @param format The format used for parsing.
     * @return The parsed Date object, or null if the string cannot be parsed.
     */
    private fun parseDate(dateStr: String, format: SimpleDateFormat): Date? {
        return try {
            format.parse(dateStr)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Shows a toast message to the user.
     *
     * @param message The message to display.
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val NameRegex = """^[a-zA-Zа-яА-Я ]+$""".toRegex()
        private val PeopleRegex = """^\d+$""".toRegex()
        private val PhoneRegex = """\+?\d{10,15}""".toRegex()
        private val EmailRegex = """^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
    }
}