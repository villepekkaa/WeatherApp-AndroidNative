WeatherApp — Android Native
===============================================

WeatherApp is a small native Android app built with Jetpack Compose and Kotlin that demonstrates location-based current weather retrieval using the OpenWeather API. The app follows a simple MVVM structure, uses Retrofit for networking and shows a settings/info screen with a dark/light theme toggle.

Features
--------
- Bottom navigation with multiple screens
- Location-based current weather (OpenWeather)
- Weather icon from OpenWeather
- MVVM architecture (ViewModels for weather, location, theme)
- Jetpack Compose UI + Navigation (Material 3)
- Theme toggle (dark / light) on Info (Settings) screen
- All UI strings moved to `strings.xml` for localization

Technologies
------------
- Kotlin (JVM)
- Jetpack Compose (Material 3)
- Navigation Compose
- Retrofit + Gson
- Google Play services Location (FusedLocationProvider)
- Gradle (Kotlin DSL)

Project structure (important files)
----------------------------------
This is a simplified view of the most important files. The real project tree contains generated/build files and other resources.

```text
app/
├─ build.gradle.kts                 # module Gradle config
├─ src/main/
│  ├─ AndroidManifest.xml
│  ├─ java/com/example/weatherapp/
│  │  ├─ MainActivity.kt            # App entry, NavHost and ViewModel wiring
│  │  ├─ model/
│  │  │  ├─ WeatherApi.kt           # Retrofit service for OpenWeather
│  │  │  ├─ WeatherData.kt          # API response data classes
│  │  │  └─ LocationLiveData.kt     # FusedLocation provider wrapper
│  │  ├─ viewmodels/
│  │  │  ├─ WeatherViewModel.kt
│  │  │  ├─ LocationViewModel.kt
│  │  │  └─ ThemeViewModel.kt
│  │  └─ ui/
│  │     ├─ appbars/
│  │     │  ├─ TopBar.kt
│  │     │  └─ BottomBar.kt
│  │     ├─ screens/
│  │     │  ├─ WeatherScreen.kt
│  │     │  └─ InfoScreen.kt
│  │     └─ theme/
│  │        ├─ Theme.kt
│  │        └─ Color.kt
│  └─ res/
│     └─ values/
│        ├─ strings.xml            # All text resources
│        └─ colors.xml             # Color resources
```

Navigation
----------
- weather (main) — shows current weather and icon
- info (settings/about) — contains theme toggle and author info

Installation / Setup
--------------------
1. Clone the repository and open it in Android Studio.

2. Add your OpenWeather API key to the project root `local.properties` file (create it if missing). Add exactly this line (no surrounding quotes):

```fish
OPENWEATHER_API_KEY=your_actual_api_key_here
```

The module `build.gradle.kts` reads this property and injects it into `BuildConfig.OPENWEATHER_API_KEY`. After changing `local.properties`, run a Clean / Rebuild so the BuildConfig is regenerated.

3. Build and run (from project root):

```fish
./gradlew clean assembleDebug
# install with adb if needed:
# adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Runtime permissions
-------------------
- The app requests `ACCESS_FINE_LOCATION` at runtime. Grant permission when prompted so the app can obtain location and fetch weather automatically.

Development
-----------
- Minimum SDK: 25
- Target / Compile SDK: 36
- Compose, Navigation and dependency versions are defined in Gradle files (see `build.gradle.kts`).

Behavior notes & troubleshooting
--------------------------------
- If `OPENWEATHER_API_KEY` is missing or empty, logs will show an empty API key and OpenWeather returns HTTP 401 Unauthorized.
- Ensure `local.properties` is in the project root and the API key line contains no quotes.
- If navigation crashes, confirm `InfoScreen` and `NavHost` routes are registered in `MainActivity.kt`.

Author / Course
---------------
- Author: Ville-Pekka Alavuotunki
- Course: Mobile Programming with Native Technologies — final project

License
-------
This repository is for educational purposes. 
