package ru.takeshiko.hungrypeople.clients

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import ru.takeshiko.hungrypeople.models.ContactMessage
import ru.takeshiko.hungrypeople.models.Product
import ru.takeshiko.hungrypeople.models.Reservation

/**
 * Object responsible for interacting with Supabase to manage data operations.
 * It provides functions to interact with the Supabase database, such as adding reservations,
 * contact messages, and retrieving products by category.
 */
object SupabaseClient {

    // Supabase project URL and API key (should be replaced with actual values).
    private const val SUPABASE_URL = "URL"
    private const val SUPABASE_KEY = "KEY"

    // Create a Supabase client to interact with the Supabase API.
    private val supabaseClient = createSupabaseClient(
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

    /**
     * Adds a contact message to the Supabase database.
     *
     * @param message The contact message object containing all details to be inserted into the database.
     * @throws Exception if the insertion fails (e.g., network error or invalid data).
     */
    suspend fun addContactMessage(message: ContactMessage) {
        // Insert the contact message data into the "contact_messages" table in Supabase.
        supabaseClient.postgrest["contact_messages"].insert(message)
    }

    /**
     * Retrieves a list of products from the Supabase database based on the specified category ID.
     *
     * @param categoryId The ID of the category to filter products by.
     * @return A list of products belonging to the specified category. Returns an empty list if an error occurs.
     */
    suspend fun getProductsByCategory(categoryId: Int): List<Product> {
        return try {
            // Query the "products" table in Supabase and filter by the provided category ID.
            supabaseClient.postgrest["products"]
                .select {
                    filter {
                        eq("category_id", categoryId)
                    }
                }
                .decodeList<Product>()
        } catch (e: Exception) {
            // Return an empty list if an exception occurs (e.g., network error or invalid data).
            emptyList()
        }
    }
}