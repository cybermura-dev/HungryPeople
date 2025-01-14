package ru.takeshiko.hungrypeople.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.yandex.mapkit.MapKitFactory
import ru.takeshiko.hungrypeople.R
import ru.takeshiko.hungrypeople.ui.adapters.ViewPagerAdapter

/**
 * ExploreActivity is an activity that allows users to explore different locations or features
 * using a ViewPager2 with a custom adapter. It also manages the lifecycle of Yandex MapKit
 * to ensure proper initialization and cleanup.
 */
class ExploreActivity : AppCompatActivity() {

    /**
     * Called when the activity is starting.
     * This method ensures that the Yandex MapKit instance is started properly.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
    }

    /**
     * Called when the activity is starting.
     * This method ensures that the Yandex MapKit instance is started properly.
     */
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    /**
     * Called when the activity is stopping.
     * This method ensures that the Yandex MapKit instance is stopped properly.
     */
    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
    }
}