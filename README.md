

# AndroidUtilities
Android developers regularly encounter situations where something which should be a single method call turns into a slog of boilerplate code, down casting and other messy practices. A simple solution is to create utility classes to encapsulate the mess, however this can lead to regularly copying files between project which is not ideal. When I encountered this problem, I decided to create this library as a central repository for my utility classes.

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-utilities:4.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).
 
## Usage
The library is divided into three packages: Helpers, views, and utilities. 

### Helpers
Helpers classes do not need to be instantiated and contain only static methods. The available helpers are:
- `AssetsHelper`: Useful for copying asset files from the assets space to a storage directory.
- `AudioFocusHelper`: Reduces boilerplate code when obtaining and abandoning audio focus.
- `BitmapEfficiencyHelper`: Simplifies the process of loading bitmaps into memory efficiently.
- `ColorHelper`: Contains methods for working with colors.
- `DimensionHelper`: Simplifies the process of converting complex dimensions (e.g. DP) to pixels.
- `PermissionsHelper`: Provides a mechanism for easily determining if particular permissions have been granted. 
- `ScreenSizeHelper`: Contains methors to query the current screen size.
- `StatusBarHelper`: Can be used to easily hiding/show the status bar. Functionality varies depending on SDK version.
- `ThemeColorHelper`: Simplifies the process of getting the primary, primary dark and accent colors of the current theme.

### Views
There is currently one class in the views package: `SquareImageView`. This class extends ImageView and provides all the same core functionality, except it forces the height and width dimensions to be equal.

### Utilities
The utilities package contains the `UiThreadUtil` interface and the `LooperUiThreadUtil` implementation. These components allow other classes to post tasks to the UI thread without directly referencing the Android framework. This allows easy testing of classes which would otherwise be difficult if not impossible to test. By using a mock/stub UiThreadUtil during testing and a real UiThreadUtil during production, classes can be tested against the JVM instead of against an Android instance.

## Licensing
This library is licensed under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Dependencies and Attribution
This library uses the following open source libraries as level 1 dependencies:
- [Android Support Library](https://developer.android.com/topic/libraries/support-library/index.html), licensed under the Apache 2.0 license.
- [Java Utilities](https://github.com/MatthewTamlin/JavaUtilities), licensed under the Apache 2.0 license.

## Compatibility
This library is compatible with Android 11 and up. The last major update significantly breaks backwards compatibility.
