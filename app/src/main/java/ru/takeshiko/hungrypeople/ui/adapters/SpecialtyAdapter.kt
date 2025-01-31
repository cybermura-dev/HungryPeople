package ru.takeshiko.hungrypeople.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.hungrypeople.R

/**
 * Adapter for displaying specialty images in a RecyclerView.
 *
 * @param images Array of image resource IDs to be displayed.
 * @param onImageSelected Callback function triggered when an image is selected.
 */
class SpecialtyAdapter(
    private val images: IntArray,
    private val onImageSelected: (Int) -> Unit
) : RecyclerView.Adapter<SpecialtyAdapter.ImageViewHolder>() {

    private var itemClickListener: ((Int) -> Unit)? = null

    /**
     * Sets a listener for item click events.
     *
     * @param listener Callback function to be invoked when an item is clicked.
     */
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    /**
     * Creates a new ViewHolder for the specialty items.
     *
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ImageViewHolder that holds the View for each specialty item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.specialty_item, parent, false)
        return ImageViewHolder(view)
    }

    /**
     * Binds the data to the ViewHolder at the specified position.
     *
     * @param holder The ViewHolder to bind the data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position], onImageSelected)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the adapter.
     */
    override fun getItemCount(): Int = images.size

    /**
     * ViewHolder class for specialty items.
     *
     * @param itemView The View that holds the specialty item layout.
     */
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.gallery_image_view)

        /**
         * Binds the image resource to the ViewHolder and sets a click listener.
         *
         * @param image The image resource ID to be displayed.
         * @param onImageSelected Callback function triggered when the image is clicked.
         */
        fun bind(image: Int, onImageSelected: (Int) -> Unit) {
            imageView.setImageResource(image)
            imageView.setOnClickListener {
                onImageSelected(bindingAdapterPosition)
                itemClickListener?.invoke(bindingAdapterPosition)
            }
        }
    }
}