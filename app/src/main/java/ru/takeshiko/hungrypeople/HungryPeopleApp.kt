package ru.takeshiko.hungrypeople

import android.app.Application
import com.yandex.mapkit.MapKitFactory

/**
 * The main application class for the HungryPeople app.
 * This class initializes global application settings and configurations,
 * such as the Yandex MapKit API key and MapKit initialization.
 * <p>
 * It extends the {@link Application} class to provide a centralized
 * place for application-wide initialization.
 * </p>
 */
class HungryPeopleApp : Application() {

    /**
     * Called when the application is starting, before any other application objects are created.
     * This method is overridden to perform application-wide initialization, such as setting
     * the Yandex MapKit API key and initializing the MapKit library.
     * <p>
     * The API key must be set before calling {@link MapKitFactory#initialize(Context)},
     * otherwise an {@link AssertionError} will be thrown.
     * </p>
     *
     * @see Application#onCreate()
     */
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("API_KEY")
        MapKitFactory.initialize(this)
    }
}