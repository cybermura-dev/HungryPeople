package ru.takeshiko.hungrypeople.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.ui.CategoryActivity

/**
 * Fragment for displaying the menu categories and handling category selection.
 */
class MenuFragment : Fragment(R.layout.fragment_delicious_menu) {
    /**
     * Called immediately after onCreateView() has returned, but before any saved state has been restored.
     *
     * @param view The View returned by onCreateView().
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSoup = view.findViewById<Button>(R.id.btn_soupe)
        val btnPizza = view.findViewById<Button>(R.id.btn_pizza)
        val btnPasta = view.findViewById<Button>(R.id.btn_pasta)
        val btnDesert = view.findViewById<Button>(R.id.btn_desert)
        val btnWine = view.findViewById<Button>(R.id.btn_wine)
        val btnBeer = view.findViewById<Button>(R.id.btn_beer)
        val btnDrinks = view.findViewById<Button>(R.id.btn_drinks)

        btnSoup.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent
                .putExtra("category", "SOUPE")
                .putExtra("category_id", 1)
            startActivity(intent)
        }

        btnPizza.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent
                .putExtra("category", "PIZZA")
                .putExtra("category_id", 2)
            startActivity(intent)
        }

        btnPasta.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent.putExtra("category", "PASTA")
                .putExtra("category_id", 3)
            startActivity(intent)
        }

        btnDesert.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent
                .putExtra("category", "DESERT")
                .putExtra("category_id", 4)
            startActivity(intent)
        }

        btnWine.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent
                .putExtra("category", "WINE")
                .putExtra("category_id", 5)
            startActivity(intent)
        }

        btnBeer.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent
                .putExtra("category", "BEER")
                .putExtra("category_id", 6)
            startActivity(intent)
        }

        btnDrinks.setOnClickListener {
            val intent = Intent(activity, CategoryActivity::class.java)
            intent
                .putExtra("category", "DRINKS")
                .putExtra("category_id", 7)
            startActivity(intent)
        }
    }
}