package ru.takeshiko.hungrypeople.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.models.Product
import java.util.Locale

/**
 * Adapter for displaying a list of products in a RecyclerView.
 *
 * @param products List of products to be displayed.
 */
class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * Creates a new ViewHolder for the product items.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ProductViewHolder that holds the View for each product item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return ProductViewHolder(view)
    }

    /**
     * Binds the data to the ViewHolder at the specified position.
     *
     * @param holder The ViewHolder to bind the data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.startAnimation(AlphaAnimation(0f, 1f).apply {
            duration = 500
        })
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the adapter.
     */
    override fun getItemCount(): Int = products.size

    /**
     * ViewHolder class for product items.
     *
     * @param itemView The View that holds the product item layout.
     */
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.item_name)
        private val itemPriceTextView: TextView = itemView.findViewById(R.id.item_price)
        private val itemDescriptionTextView: TextView = itemView.findViewById(R.id.item_description)

        /**
         * Binds the product data to the ViewHolder.
         *
         * @param product The product to be displayed.
         */
        fun bind(product: Product) {
            itemNameTextView.text = product.title
            itemPriceTextView.text = String.format(Locale.getDefault(), itemView.context.getString(R.string.price_format), product.price)
            itemDescriptionTextView.text = product.description
        }
    }
}