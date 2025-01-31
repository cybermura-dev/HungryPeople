package ru.takeshiko.hungrypeople.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.ui.adapters.GalleryAdapter

/**
 * Fragment for displaying a gallery of images with a selected image view and dots indicator.
 */
class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private var images: IntArray = intArrayOf(
        R.drawable.img_slider_1,
        R.drawable.img_slider_2,
        R.drawable.img_slider_3,
        R.drawable.img_slider_4
    )

    private var selectedImageView: ImageView? = null
    private lateinit var dotsContainer: LinearLayout

    /**
     * Called immediately after onCreateView() has returned, but before any saved state has been restored.
     *
     * @param view The View returned by onCreateView().
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedImageView = view.findViewById(R.id.selected_image_view)
        dotsContainer = view.findViewById(R.id.dots_container)

        selectedImageView?.setImageResource(images[0])

        val recyclerView = view.findViewById<RecyclerView>(R.id.simple_gallery)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), images.size)
        val adapter = GalleryAdapter(images) { position ->
            selectedImageView?.let { imageView ->
                val fadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)
                fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                    override fun onAnimationStart(animation: android.view.animation.Animation?) {}
                    override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                        imageView.setImageResource(images[position])
                        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                        imageView.startAnimation(fadeIn)
                    }
                    override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
                })
                imageView.startAnimation(fadeOut)
            }
        }
        recyclerView.adapter = adapter

        createDots(images.size)
        adapter.setOnItemClickListener { position ->
            selectDot(position)
            recyclerView.scrollToPosition(position)
        }
    }

    /**
     * Creates dots for the dots indicator.
     *
     * @param count The number of dots to create.
     * @param selectedPosition The position of the initially selected dot.
     */
    private fun createDots(count: Int, selectedPosition: Int = 0) {
        dotsContainer.removeAllViews()
        for (i in 0 until count) {
            val dotView = View(requireContext())
            dotView.layoutParams = LinearLayout.LayoutParams(10.dpToPx(), 10.dpToPx()).apply {
                marginEnd = 5.dpToPx()
            }
            dotView.setBackgroundResource(if (i == selectedPosition) R.drawable.selected_dot else R.drawable.unselected_dot)
            dotsContainer.addView(dotView)
        }
    }

    /**
     * Selects the dot at the specified position.
     *
     * @param position The position of the dot to select.
     */
    private fun selectDot(position: Int) {
        for (i in 0 until dotsContainer.childCount) {
            val dotView = dotsContainer.getChildAt(i)
            dotView.setBackgroundResource(if (i == position) R.drawable.selected_dot else R.drawable.unselected_dot)
        }
    }

    /**
     * Converts density-independent pixels (dp) to pixels (px).
     *
     * @return The pixel value corresponding to the given dp value.
     */
    private fun Int.dpToPx(): Int {
        return (this * requireContext().resources.displayMetrics.density).toInt()
    }
}