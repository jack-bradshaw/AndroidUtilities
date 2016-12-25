#AndroidUtilities
A library containing various utilities and views for use in Android development. The components of the library are divided into five packages: Collections, helpers, testing, utilities and views. Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-utilities:2.6.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).

Important note: This library depends on code which will cause an InvalidPackage lint error. To disable this error, add the following to the android scope of the build.gradle file:

```
lintOptions {
 disable 'InvalidPackage'
}
 ```
 
## Helpers
The helpers package contains classes designed to eliminate boilerplate code when doing simple Android tasks. Helpers classes do not need to be instantiated and contain only static methods. The available helpers are:
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

## Collections
The collections package contains custom collections as well as classes for working with collections. The package contains:
 - `ArrayListWithCallbacks`: A subclass of ArrayList which delivers callbacks whenever the contents of the list are modified.
 - `Grouper`: An interface definition for sorting the contents of a collection into a set of smaller collections.
 - `KeyBasedGrouper`: A simple implementation of the Grouper interface.
 
## Views
There is currently one class in the views package: `SquareImageView`. This View is a subclass of the ImageView class which constrains the height and width to be equal.

## Testing
The testing package contains the `@Tested` annotation. This annotation allows you to keeping track of which classes have been tested, and whether or not the tests require the android framework.

## Utilities
The utilities package contains useful classes which must be instantiated to be used. The package contains:
- `UiThreadUtil`: An interface definition for executing tasks on the UI thread.
- `LooperUiThreadUtil`: An implementation of the UiThreadUtil which uses Looper objects to post tasks.

The UiThreadUtil interface can be used with dependency injection to allow a class to post events on the UI thread without directly depending on the Android framework. A mock UiThreadUtil can be injected during testing so that testing can be done without instrumentation, and a real UiThreadUtil can be injected in production.

## Licensing
This library is licenced under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Compatibility
This library is compatible with Android 11 and up. The last major update removed most of the classes in the testing package, as these were causing InvalidPackage warnings. The classes have been moved to [a separate library](https://github.com/MatthewTamlin/AndroidTestingTools).
