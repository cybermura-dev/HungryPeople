package ru.takeshiko.hungrypeople.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.clients.SupabaseClient
import ru.takeshiko.hungrypeople.ui.adapters.ProductAdapter

/**
 * Activity for displaying products of a specific category.
 *
 * This activity retrieves products from a Supabase database based on the provided category ID
 * and displays them in a RecyclerView.
 */
class CategoryActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Retrieve category name and ID from the intent
        val category = intent.getStringExtra("category")
        val categoryId = intent.getIntExtra("category_id", 1)

        // Set the category title in the TextView
        val categoryTextView = findViewById<TextView>(R.id.category_title)
        categoryTextView.text = category ?: "ERROR"

        // Initialize RecyclerView and ProgressBar
        recyclerView = findViewById(R.id.recycler_view_menu)
        progressBar = findViewById(R.id.progress_bar)

        // Show progress bar and hide RecyclerView while loading data
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        // Launch a coroutine to fetch products asynchronously
        lifecycleScope.launch {
            // Fetch products from Supabase based on the category ID
            val products = SupabaseClient.getProductsByCategory(categoryId)

            // Hide progress bar and show RecyclerView after data is loaded
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            // Set up RecyclerView with a LinearLayoutManager and ProductAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@CategoryActivity)
            val adapter = ProductAdapter(products)
            recyclerView.adapter = adapter
            recyclerView.itemAnimator = DefaultItemAnimator()
        }
    }
}