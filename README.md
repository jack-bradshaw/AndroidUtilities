#AndroidUtilities
This library contains various helper classes, utilities and views for use in Android development. To use this library, add `compile 'com.matthew-tamlin:android-utilities-library:2.0.0'` to your dependencies. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).

## Helpers
The helpers package contains classes with useful static methods, designed to eliminate boilerplate code when performing simple Android tasks. The following helper classes are available in the helpers package:
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
There is currently only one class in the collections package: `ArrayListWithCallbacks`. Whenever this collection is modified callbacks events are delivered to registered listeners. Each callback contains information about the List which was modified and the nature of the modification.

## Views
There is currently one class in the views package: `SquareImageView`. This View is a simple extension of the ImageView class, which forces one of the dimensions (width or height) to equal the other.

## Licensing
This library is licenced under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Compatibility
This library is compatible with Android 11 and up. Version 2.0.0 breaks backward compatibility with previous versions.