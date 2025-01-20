package ru.takeshiko.hungrypeople.clients

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import ru.takeshiko.hungrypeople.models.ContactMessage
import ru.takeshiko.hungrypeople.models.Reservation

/**
 * Object responsible for interacting with Supabase to manage reservations.
 * It provides functions to add reservations to the Supabase database.
 */
object SupabaseClient {

    // Supabase project URL and API key (should be replaced with actual values).
    private const val SUPABASE_URL = "URL"
    private const val SUPABASE_KEY = "KEY"

    // Create a Supabase client to interact with the Supabase API.
    private val supabaseClient  = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Postgrest) // Install Postgrest for interacting with the database.
    }

    /**
     * Adds a reservation to the Supabase database.
     *
     * @param reservation The reservation object containing all details to be inserted into the database.
     * @throws Exception if the insertion fails (e.g., network error or invalid data).
     */
    suspend fun addReservation(reservation: Reservation) {
        // Insert the reservation data into the "reservations" table in Supabase.
        supabaseClient.postgrest["reservations"].insert(reservation)
    }

    suspend fun addContactMessage(message: ContactMessage) {
        // Insert the reservation data into the "contact_messages" table in Supabase.
        supabaseClient.postgrest["contact_messages"].insert(message)
    }
}
