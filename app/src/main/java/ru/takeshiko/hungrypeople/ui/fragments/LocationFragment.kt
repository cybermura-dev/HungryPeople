package ru.takeshiko.hungrypeople.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import ru.takeshiko.hungrypeople.R

/**
 * LocationFragment is a fragment that displays a map with a predefined location.
 * It uses Yandex MapKit to render the map and set the camera position to a specific point.
 */
class LocationFragment : Fragment(R.layout.fragment_location) {

    private lateinit var mapView: MapView

    /**
     * Called when the fragment's view is created.
     * Initializes the MapView and sets up the map.
     *
     * @param view The root view of the fragment.
     * @param savedInstanceState Bundle object that contains the fragment's previous state, if any.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.map_view)
        setupMap()
    }

    /**
     * Configures the map by setting the camera position to a predefined location.
     * The location is set to coordinates (55.7522, 37.6156) with a zoom level of 14.
     */
    private fun setupMap() {
        val map = mapView.mapWindow.map
        val restaurantLocation = Point(55.7522, 37.6156)

        val cameraPosition = CameraPosition(restaurantLocation, 14.0f, 0.0f, 0.0f)
        map.move(cameraPosition)
    }
}