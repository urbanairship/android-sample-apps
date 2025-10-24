# Android Sample App — Setup

This is a sample Android application demonstrating the Urban Airship (Airship) SDK integration.

Note: this project expects an Urban Airship (Airship) configuration file and the Google services configuration file for Firebase/Google Play services.

### Quick setup checklist

1. Copy the Airship config sample:
   - The sample config is located at `app/src/main/assets/airshipconfig.properties.sample`.
   - Copy it to `app/src/main/assets/airshipconfig.properties` and enter your credentials.
2. Add your `google-services.json` file:
   - Download `google-services.json` from the Firebase console for your project.
   - Place the file in the `app/` directory.

### Detailed setup

#### 1. Copy the Airship configuration sample and fill in your credentials

From the project root, copy the sample Airship config file to create your own:

```bash
cp app/src/main/assets/airshipconfig.properties.sample app/src/main/assets/airshipconfig.properties
```

Open the newly created `airshipconfig.properties` and fill in your Airship credentials (app key, app secret).

#### 2. Add `google-services.json` (required for push notifications via FCM)

Download `google-services.json` from the Firebase console. Then copy or move the file into the `app/` directory of this project:

```bash
cp ~/Downloads/google-services.json app/
```

The Android Gradle plugin expects `google-services.json` to live at `app/google-services.json`.

#### 3. Build and run

From the project root, use the included Gradle wrapper to build:

```bash
./gradlew clean assembleDebug
```

To install on a connected device or emulator:

```bash
./gradlew installDebug
```

Or open the project in Android Studio and run the `app` configuration.

### Verification
- When the app starts, it should initialize the Airship SDK using values in `airshipconfig.properties` and any Firebase integrations should be available if `google-services.json` is correct.
- Check logcat for messages from `UALib` (Airship) to confirm initialization.

### Troubleshooting

- "Missing airshipconfig.properties" or runtime errors referencing missing config: make sure you copied the sample file and filled required values.
- Gradle build errors related to Google Services: ensure `google-services.json` is in `app/` and the Google services plugin is applied in `app/build.gradle`.
- If you see any Firebase initialization errors, double-check the package name (applicationId) in `app/build.gradle` matches the package registered in the Firebase console.
- If builds are stale, try `./gradlew clean` then rebuild.

### Security / VCS

Ensure that the `airshipconfig.properties` and `google-services.json` files are not included in version control.
The `.gitignore` file in this project already excludes these files by default.
