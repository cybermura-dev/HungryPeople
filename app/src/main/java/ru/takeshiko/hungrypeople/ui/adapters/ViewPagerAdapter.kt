package ru.takeshiko.hungrypeople.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.takeshiko.hungrypeople.ui.fragments.ContactFragment
import ru.takeshiko.hungrypeople.ui.fragments.LocationFragment

/**
 * ViewPagerAdapter is a custom adapter for ViewPager2 that manages fragments.
 * It extends {@link FragmentStateAdapter} to handle the creation and lifecycle of fragments
 * used in the ViewPager2. This adapter supports two fragments: {@link ContactFragment} and {@link LocationFragment}.
 */
class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    /**
     * Returns the total number of fragments managed by the adapter.
     *
     * @return The number of fragments, which is 2 in this case.
     */
    override fun getItemCount(): Int = 2

    /**
     * Creates and returns a fragment for the specified position.
     * This method is responsible for instantiating the appropriate fragment based on the position.
     *
     * @param position The position of the fragment in the ViewPager2.
     * @return A new instance of {@link ContactFragment} for position 0,
     *         or {@link LocationFragment} for position 1.
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactFragment()
            1 -> LocationFragment()
            else -> ContactFragment() // Fallback to ContactFragment for unexpected positions
        }
    }
}
