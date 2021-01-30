# AndroidUtilities
Simple utilities I've found to be userful in Android apps and libraries.

**SUPPORT NOTICE: This library is now STABLE. It is no longer under active development, however pull requests from others are still being accepted.**

## Dependency
To use the library, add the following to your gradle build file:
```groovy
repositories {
  jcenter()
}

dependencies {
  implementation 'com.matthew-tamlin:android-utilities:5.1.2'
}
```

Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).
 
## Helpers
The helpers package contains the following classes:
- AssetsHelper
- AudioFocusHelper
- BitmapEfficiencyHelper
- ColorHelper
- DimensionHelper
- InternetHelper
- ScreenSizeHelper
- ThemeColorHelper

### AssetsHelper
Provides a simple means of copying assets to a file directory.
```java
AssetsManager manager = context.getAssets();
File targetDir = context.getFilesDir();

// You can copy just one asset
AssetsHelper.copyAssetsToDirectory(manager, targetDir, "picture.png");

// Or you can copy multiple assets using var-args
AssetsHelper.copyAssetsToDirectory(manager, targetDir, "db_config.xml", "default_values");
```

### AudioFocusHelper
Reduces boilerplate code when obtaining and abandoning audio focus.
```java
AudioFocusHelper.requestStreamAlarmFocus(context, new OnAudioFocusChangeListener() {
  @Override
  public void onAudioFocusChange(int focusChange) {
    // Do something
  });
    
AudioFocusHelper.abandonFocus(context, new OnAudioFocusChangeListener() {
  @Override
  public void onAudioFocusChange(int focusChange) {
    // Do something else
  });
```

### BitmapEfficiencyHelper
Encapsulates the complexities and boilerplate code of efficiently decoding artwork.
```java
ImageView imageView = (ImageView) context.findViewById(R.id.image_view);

Bitmap image = BitmapEfficiencyHelper.decodeResource(
    context.getResoures(), 
    R.raw.image, 
    imageView.getWidth(), 
    imageView.getHeight());

imageView.setImageBitmap(image);
```

### ColorHelper
Provides several useful methods for working with colors.

To gradually change the color of something:
```java
FrameLayout layout = (FrameLayout) context.findViewById(R.id.layout);

final int startColor = Color.RED;
final int endColor = Color.BLUE;

ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(final ValueAnimator animation) {
        ColorHelper.blendColors(
        startColor, 
        endColor, 
        animation.getAnimatedFraction());
    }
});
```

To generate random colors:
```java
// The transparency can be fixed at fully opaque
int randomColorWithFullOpacity = ColorHelper.createRandomColor(false);

// Or it can be randomised just like the RGB channels
int randomColorWithRandomOpacity = ColorHelper.createRandomColor(true);
```

To pick the text color to use against a colored background:
```java
// Some view directly behind the text
int backgroundColor = someView.getBackgroundColor();

// Returns white or black, whichever maximises readability of the text
int textColor = ColorHelper.calculateBestTextColor(backgroundColor);
```

### DimensionHelper
Converts complex units such as DP and SP to pixels.
```java
DimensionHelper.dpToPx(context, someDpDimension);
DimensionHelper.spToPx(context, someSpDimension);
DimensionHelper.ptToPx(context, somePtDimension);
```

### InternetHelper
Provides information about the current internet connection (if any).
```java
ConnectionType connType = InternetHelper.getInternetConnectionType(context);

if (connType == null) {
    disableUpload();
} else if (connType == ConnectionType.WIFI) {
    enableFullUpload();
} else {
    enableCompressedUpload();
}
```

### ScreenSizeHelper
Provides information about the device screen.

To get the physical dimensions of the screen:
```java
int screenWidth = ScreenSizeHelper.getScreenWidth(context);
int screenHeight = ScreenSizeHelper.getScreenHeight(context);
```

To get the screen size bucket:
```java
public int getLayoutResId(Context context) {
    ScreenSize size = ScreenSizeHelper.getScreenSize(context);

    switch (size) {
        case SMALL: return R.layout.this_screen_is_too_small;
        case NORMAL: return R.layout.this_screen_is_just_right;
        case LARGE: return R.layout.this_screen_is_too_large;
        default: return R.layout.dont_play_with_bears;
    }
}
```

### ThemeColorHelper
Makes it easy to get the primary, primary dark and accent colors of the current theme.
```java
int defaultColor = Color.WHITE;

int primaryColor = ThemeColorHelper.getPrimaryColor(context, defaultColor);
int primaryDarkColor = ThemeColorHelper.getPrimaryDarkColor(context, defaultColor);
int accentColor = ThemeColorHelper.getAccentColor(context, defaultColor);
```

## Compatibility
This library is compatible with Android 14 and up.
