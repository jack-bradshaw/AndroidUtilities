#AndroidUtilities
This library contains various helper classes, collections and views for use in Android development. Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-utilities:2.2.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).

## Checkers
The checkers package contains classes for checking arguments without boilerplate code. The available checkers are:
- `NullChecker`: Utility for checking if arguments are null.
- `IntChecker`: Utility for checking if integer arguments meet numeric requirements.

## Helpers
The helpers package contains classes with useful static methods, designed to eliminate boilerplate code when performing simple Android tasks. The available helpers are:
- `AssetsHelper`: Utility for copying asset files from the assets space to a storage directory.
- `AudioFocusHelper`: Utility for obtaining and abandoning audio focus.
- `BitmapEfficiencyHelper`: Utility for efficiently decoding bitmap images.
- `ColorHelper`: Utility for blending colors together.
- `DimensionHelper`: Utility for converting complex dimensions (such as DP) to pixels.
- `PermissionsHelper`: Utility for determining if a series of permissions have been granted. 
- `ScreenSizeHelper`: Utility for querying information about the device screen size.
- `StatusBarHelper`: Utility for hiding and showing the status bar. Functionality varies depending on SDK version.
- `ThemeColorHelper`: Utility for querying the colors of a theme.

## Collections
The collections class contains three components: The `ArrayListWithCallbacks` class, the `Grouper` interface and the `KeyBasedGrouper` class.
 - `ArrayListWithCallbacks` is a subclass of the standard Java ArrayList which delivers callbacks to registered listeners whenever the Collection is modified.
 - `KeyBasedGrouper` implements the `Grouper` interface. It allows a Collection to be easily grouped into smaller Collections.

## Views
There is currently one class in the views package: `SquareImageView`. This View is a simple extension of the ImageView class, which forces one of the dimensions (width or height) to equal the other.

## Licensing
This library is licenced under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Compatibility
This library is compatible with Android 11 and up. Version 2.0.0 breaks backward compatibility with previous versions.
