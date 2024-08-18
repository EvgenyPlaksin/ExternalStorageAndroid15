# Media Access Showcase for Android 15

Media Access Showcase for Android 15 is an Android application built using Jetpack Compose that demonstrates how to access and manage media files on Android devices, particularly focusing on the changes introduced in Android 13 (API level 33) and above. This project serves as a guide for developers to understand and implement media access while handling the updated permissions model.

## Features

- **Media Access Across Versions**: Handles media access permissions dynamically, ensuring compatibility with both older and newer Android versions.
- **Media Types Supported**: Displays images, videos (with thumbnails), and audio files retrieved from external storage.
- **Jetpack Compose UI**: A modern, responsive UI built using Jetpack Compose.
- **Dynamic Permissions Handling**: Requests runtime permissions based on the device's API level.

## Prerequisites

- **Minimum SDK**: 26
- **Target SDK**: 35
- **Compile SDK**: 35

## Technologies Used

- **Jetpack Compose**: For building a declarative and modern user interface.
- **MediaStore API**: To query and access media files from the device's external storage.
- **Kotlin Coroutines**: For managing background tasks like media file loading.
- **Coil**: For loading and displaying images and video thumbnails.

## How It Works

1. **Permissions Handling**: On startup, the app requests the necessary media access permissions. The permissions requested vary based on the Android version.
2. **Media Retrieval**: The `MediaReader` class queries the device’s external storage for media files (images, videos, and audio) using the `MediaStore` API.
3. **Displaying Media**: The retrieved media files are displayed in a list using the `LazyColumn` composable. Images and video thumbnails are displayed using `Coil`.
4. **Media Type Differentiation**: The app differentiates between images, videos, and audio, displaying appropriate icons or thumbnails.

## Limitations

- **Permissions on Older Android Versions**: The app handles older permissions models but may have limitations due to restrictions on media access introduced in newer Android versions.

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/EvgenyPlaksin/ExternalStorageAndroid15.git
    ```
2. Open the project in Android Studio.
3. Build and run the project on a device or emulator with a minimum API level of 26.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
