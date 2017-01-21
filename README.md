

#AndroidUtilities
Android developers regularly encounter situations where something which should be a simple one-line method call turns into a slog of boilerplate code, down casting and other messy practices. A simple solution is to create utility classes to encapsulate the mess, however this can lead to regularly copying files between project which is not ideal. When I encountered this problem, I decided to created this library as a central repository for my utility classes.

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-utilities:3.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).
 
## Usage
The library is divided into five packages: Helpers, collections, helpers, views, testing and utilities. 

### Helpers
Helpers classes do not need to be instantiated and contain only static methods. The available helpers are:
- `AssetsHelper`: Contains static methods for copying asset files from the assets space to a storage directory.
- `AudioFocusHelper`: Contains static methods for obtaining and abandoning audio focus.
- `BitmapEfficiencyHelper`: Contains static methods for efficiently decoding bitmap images.
- `ColorHelper`: Contains static methods for blending colors together.
- `DimensionHelper`: Contains static methods for converting complex dimensions (such as DP and SP) to pixels.
- `NullHelper`: Contains static methods for safely working with variables which could be null.
- `PermissionsHelper`: Contains static methods for determining if one or more permissions have been granted. 
- `ScreenSizeHelper`: Contains static methods for getting information about the device screen size.
- `StatusBarHelper`: Contains static methods for hiding and showing the status bar. Functionality varies depending on SDK version.
- `ThemeColorHelper`: Contains static methods for getting the primary, primary dark and accent colors of the current theme.

### Collections
The collections package contains custom collections as well as classes for working with collections. The package contains:
 - `Grouper`: An interface definition for sorting the contents of a collection into a set of smaller collections.
 - `KeyBasedGrouper`: A simple implementation of the Grouper interface.
 
### Views
There is currently one class in the views package: `SquareImageView`. This View is a subclass of the ImageView class which constrains the height and width to be equal.

### Testing
The testing package contains the `@Tested` annotation. This annotation allows you to keeping track of which classes have been tested, and whether or not the tests require the android framework. This package previously contained

### Utilities
The utilities package contains useful classes which must be instantiated to be used. The package contains:
- `UiThreadUtil`: An interface definition for executing tasks on the UI thread.
- `LooperUiThreadUtil`: An implementation of the UiThreadUtil which uses Looper objects to post tasks.

The UiThreadUtil interface can be used with dependency injection to allow a class to post events on the UI thread without directly depending on the Android framework. A mock UiThreadUtil can be injected during testing so that testing can be done without instrumentation, and a real UiThreadUtil can be injected in production.

## Licensing
This library is licensed under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Dependencies and Attribution
This library uses the following open source libraries as level 1 dependencies:
- [Android Support Library](https://developer.android.com/topic/libraries/support-library/index.html), licensed under the Apache 2.0 license.
- [Java Utilities](https://github.com/MatthewTamlin/JavaUtilities), licensed under the Apache 2.0 license.

## Compatibility
This library is compatible with Android 11 and up. The last major update removed most of the classes in the testing package, as these were causing InvalidPackage warnings. The classes have been moved to [a separate library](https://github.com/MatthewTamlin/AndroidTestingTools).
