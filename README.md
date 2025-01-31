# HungryPeople

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
  - [Prequisites](#prequisites)
  - [Steps](#steps)
- [Usage](#usage)
  - [Table Reservations](#table-reservations)
  - [Menu Browsing](#menu-browsing)
  - [Map Integration](#map-integration)
  - [Gallery](#gallery)
  - [Contact Form](#contact-form)
- [How it Works](#how-it-works)
  - [Navigation](#navigation)
  - [Database Integration](#database-integration)
  - [Networking](#networking)
- [Project Structure](#project-structure)
  - [Core Components](#core-components)
  - [UI Components](#ui-components)
  - [Database Models](#database-models)
- [Build Configuration](#build-configuration)
  - [Gradle Dependencies](#gradle-dependencies)
  - [AndroidManifest Configuration](#androidmanifest-configuration)
- [API References](#api-references)
  - [Supabase](#supabase)
  - [Yandex MapKit](#yandex-mapkit)
- [License](#license)

## Overview

**HungryPeople** is a restaurant management app designed to help users make reservations, browse menus, and explore a restaurant's offerings. The app includes interactive features like gallery browsing, location mapping, and a contact form to facilitate communication with the restaurant.

## Features

- **Table Reservations:** Users can book tables for different times and party sizes.
- **Menu Browsing:** Explore the restaurant's menu, including food categories and special items.
- **Map Integration:** Visualize the restaurant's location on an interactive map using Yandex MapKit.
- **Gallery:** View the restaurant's gallery to explore photos of dishes, ambiance, and events.
- **Contact Form:** Reach out to the restaurant through a contact form for inquiries or feedback.

## Technologies

The project utilizes the following technologies:

- **Kotlin:** The app is written in Kotlin, leveraging modern Android development practices.
- **Supabase:** Backend-as-a-service (BaaS) for data management.
- **Ktor:** For networking and API requests.
- **Yandex MapKit:** Map service for location integration.

## Installation

### Prerequisites

Ensure the following tools and SDKs are installed before setting up the project:

- **Android Studio:** Make sure you have [Android Studio](https://developer.android.com/studio) installed.
- **Android Emulator or Device:** A physical Android device or emulator to run the app.
- **Android SDK** (min SDK: 26).
- **Kotlin** version 2.0.0 or higher.
- **Gradle** version 8.7.3.
- **Supabase Account:** Set up a Supabase account to manage your app's backend.

### Steps

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/takeshikodev/HungryPeople.git
   cd HungryPeople
   ```

2. **Open in Android Studio:** Open the project in Android Studio.

3. **Sync the project with Gradle:**
  - Go to **File** > **Sync Project with Gradle Files**.

4. **Add your Supabase URL and API Key:** In the `SupabaseClient.kt` file, replace the following placeholders with your actual Supabase URL and API key:
   ```kotlin
   private const val SUPABASE_URL = "https://your-project.supabase.co"
   private const val SUPABASE_KEY = "your-supabase-api-key"
   ```

5. **Add your Yandex MapKit API Key:** In the `HungryPeopleApp.kt` file, replace the following placeholder with your actual Yandex MapKit API key:
   ```kotlin
   MapKitFactory.setApiKey("your-yandex-mapkit-api-key")
   ```
  
7. **Run the App:** Use the Android Studio toolbar to select a device and click Run to start the app.

## Usage

### Table Reservations

Users can browse available tables and make reservations for a desired time and date. Simply follow these steps in the app interface:
1. Open the Book a Table screen.
2. Choose the number of people.
3. Select the date and time.
4. Confirm the reservation.

### Menu Browsing

The **Menu** screen lets users explore different food categories, including appetizers, mains, and desserts.
1. Open the **Menu** tab.
2. Browse through categories like **Drinks**, **Soupes**, **Desserts** and etc.
3. Select a dish to view detailed information.

### Map Integration

The application integrates Yandex MapKit for visualizing the restaurant's location.
1. Open the **Location** screen.
2. The map will display the restaurant’s location and allow users to get directions.

### Gallery

The **Gallery** section displays photos of the restaurant's dishes and events.
1. Go to the **Gallery** tab.
2. Scroll through images and tap to view them in full screen.

### Contact Form

Reach out to the restaurant via the **Contact** tab.
1. Fill in your name, email, and message.
2. Submit the form, and the restaurant will respond to your inquiry.

## How it Works

### Navigation

The app uses Android's native **Navigation Component** to handle in-app navigation between different screens such as the reservation system, menu, and gallery.

### Database Integration

**Supabase** is used for managing user data and reservations. The app communicates with the Supabase backend via RESTful APIs, which handles:

- **Reservations:** Storing reservation data.
- **Contact Messages:** Storing messages sent via the contact form.
- **Product Data:** Storing product details and categories for the menu.

### Networking

Network requests are handled using **Ktor** client, interacting with Supabase and external APIs for map and location services.

## Project Structure

Here's an overview of the project's directory structure:

```bash
src
│   ├───main
│   │   │   AndroidManifest.xml
│   │   │
│   │   ├───java
│   │   │   └───ru
│   │   │       └───takeshiko
│   │   │           └───hungrypeople
│   │   │               │   HungryPeopleApp.kt
│   │   │               │
│   │   │               ├───clients
│   │   │               │       SupabaseClient.kt
│   │   │               │
│   │   │               ├───models
│   │   │               │       ContactMessage.kt
│   │   │               │       Product.kt
│   │   │               │       Reservation.kt
│   │   │               │
│   │   │               └───ui
│   │   │                   │   BookTableActivity.kt
│   │   │                   │   CategoryActivity.kt
│   │   │                   │   ExploreActivity.kt
│   │   │                   │   HomeActivity.kt
│   │   │                   │   SplashActivity.kt
│   │   │                   │
│   │   │                   ├───adapters
│   │   │                   │       GalleryAdapter.kt
│   │   │                   │       ProductAdapter.kt
│   │   │                   │       SpecialtyAdapter.kt
│   │   │                   │       ViewPagerAdapter.kt
│   │   │                   │
│   │   │                   ├───animators
│   │   │                   │       ViewClickAnimator.kt
│   │   │                   │
│   │   │                   └───fragments
│   │   │                           AboutUsFragment.kt
│   │   │                           ContactFragment.kt
│   │   │                           GalleryFragment.kt
│   │   │                           LocationFragment.kt
│   │   │                           MenuFragment.kt
│   │   │                           OurTeamFragment.kt
│   │   │                           PrivateEventsFragment.kt
│   │   │                           SpecialtiesFragment.kt
```

### Core Components

Core components handle the essential functionalities of the app, such as networking, database interaction, and core services:

- **`SupabaseClient.kt`**: Handles interactions with the Supabase backend for managing reservations, contact messages, and fetching products from the database.
- **`HungryPeopleApp.kt`**: Initializes global settings for the application, including the Yandex MapKit API key and MapKit setup.
  
### UI Components

UI components define the app's user interface elements, including activities, fragments, and UI-specific logic:

- **Activities:**
  - `HomeActivity.kt`: The main entry point of the app where users can start their experience.
  - `BookTableActivity.kt`: Allows users to make table reservations.
  - `ExploreActivity.kt`: Allows users to explore different menu categories and items.
  - `CategoryActivity.kt`: Displays products from a specific category.
  - `SplashActivity.kt`: Shows a splash screen when the app is launched.
  
- **Fragments:**
  - `AboutUsFragment.kt`: Displays information about the restaurant.
  - `ContactFragment.kt`: Displays contact information and a form to send a message.
  - `GalleryFragment.kt`: Displays a gallery of images related to the restaurant.
  - `LocationFragment.kt`: Displays restaurant location on a map.
  - `MenuFragment.kt`: Displays the menu with different food options.
  - `OurTeamFragment.kt`: Displays the team behind the restaurant.
  - `PrivateEventsFragment.kt`: Displays information about private events and bookings.
  - `SpecialtiesFragment.kt`: Displays the restaurant's special dishes.

- **Adapters:**
  - `GalleryAdapter.kt`: Binds gallery data to the UI.
  - `ProductAdapter.kt`: Binds product data to the menu UI.
  - `SpecialtyAdapter.kt`: Binds specialty items to the UI.
  - `ViewPagerAdapter.kt`: Binds data to view pager components for sliding views.

- **Animators:**
  - `ViewClickAnimator.kt`: Handles animations for UI elements when clicked.

### Database Models

These classes define the data models used for storing and retrieving data from the database:

- **`ContactMessage.kt`:** Defines the structure for storing contact messages sent by users.
- **`Product.kt`:** Represents a product in the menu with attributes like name, price, and category.
- **`Reservation.kt`:** Represents a reservation made by a user, including details like date, time, and number of people.

## Build Configuration

### Gradle Dependencies

The application uses the following dependencies:

- **Supabase** for backend interactions.
- **Ktor** for HTTP requests.
- **Yandex MapKit** for map integration.
- **AndroidX** libraries for UI components.

```gradle
dependencies {

    // App dependencies
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.viewpager2)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.dotsindicator)

    // Supabase dependencies
    implementation(platform(libs.bom))
    implementation(libs.postgrest.kt)

    // Networking
    implementation(libs.ktor.client.android)

    // SDK Yandex Map Kit
    implementation(libs.maps.mobile)
}
```

```toml
[versions]
agp = "8.7.3"
appcompat = "1.7.0"
bom = "3.0.3"
constraintlayout = "2.2.0"
dotsindicator = "5.1.0"
kotlin = "2.0.0"
ktorClientAndroid = "3.0.3"
mapsMobile = "4.10.1-lite"
material = "1.12.0"
viewpager2 = "1.1.0"

[libraries]
androidx-constraintlayout = { module = "androidx.constraintlayout:constraintlayout", version.ref = "constraintlayout" }
androidx-viewpager2 = { module = "androidx.viewpager2:viewpager2", version.ref = "viewpager2" }
androidx-appcompat = { module = "androidx.appcompat:appcompat", version.ref = "appcompat" }
bom = { module = "io.github.jan-tennert.supabase:bom", version.ref = "bom" }
dotsindicator = { module = "com.tbuonomo:dotsindicator", version.ref = "dotsindicator" }
ktor-client-android = { module = "io.ktor:ktor-client-android", version.ref = "ktorClientAndroid" }
maps-mobile = { module = "com.yandex.android:maps.mobile", version.ref = "mapsMobile" }
material = { module = "com.google.android.material:material", version.ref = "material" }
postgrest-kt = { module = "io.github.jan-tennert.supabase:postgrest-kt" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlinx-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version = "1.9.0" }
```

### AndroidManifest Configuration

The `AndroidManifest.xml` file includes necessary permissions and activity configurations, such as the Internet permission for network requests and the setup for Yandex MapKit.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:name=".HungryPeopleApp"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.HungryPeople"
        tools:targetApi="31">

        <activity
            android:name=".ui.CategoryActivity"
            android:exported="false"
            android:screenOrientation="locked"
            tools:ignore="DiscouragedApi" />

        <activity
            android:name=".ui.ExploreActivity"
            android:exported="false"
            android:screenOrientation="locked"
            tools:ignore="DiscouragedApi" />

        <activity
            android:name=".ui.BookTableActivity"
            android:exported="false"
            android:screenOrientation="locked"
            tools:ignore="DiscouragedApi" />

        <activity
            android:name=".ui.HomeActivity"
            android:exported="false"
            android:screenOrientation="locked"
            tools:ignore="DiscouragedApi" />

        <activity
            android:name=".ui.SplashActivity"
            android:exported="true"
            android:screenOrientation="locked"
            android:theme="@style/Theme.HungryPeople.SplashScreen"
            tools:ignore="DiscouragedApi">

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

## API Reference

### Supabase

**Supabase** is used to manage user authentication, reservations, and other backend tasks. Check the [Supabase documentation](https://supabase.com/docs/reference/kotlin/introduction) for more information.

### Yandex MapKit

**Yandex MapKit** allows you to display maps and integrate geolocation. Visit the [Yandex MapKit documentation](https://yandex.ru/dev/mapkit/doc/ru/android/generated/getting_started?ysclid=m6l1pogw1e233061723) for details.

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/takeshikodev/HungryPeople/blob/master/LICENSE) file for details.
