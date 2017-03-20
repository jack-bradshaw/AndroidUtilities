# AndroidUtilities
Android developers regularly encounter situations where something which should be a simple method call turns into a slog of boilerplate code, down casting and other messy practices. A simple solution is to create utility classes to encapsulate the mess, however this can lead to regularly copying files between project which is not ideal. When I encountered this problem, I decided to create this library as a central repository for my utility classes.

## Download
Releases are made available through jCentre. Add `compile 'com.matthew-tamlin:android-utilities:5.0.0'` to your gradle build file to use the latest version. Older versions are available in the [maven repo](https://bintray.com/matthewtamlin/maven/AndroidUtilities/view).
 
## Helpers
The helpers package contains the following classes:
- AssetsHelper
- AudioFocusHelper
- BitmapEfficiencyHelper
- ColorHelper
- DimensionHelper
- InternetHelper
- PermissionsHelper
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
DimensionHelper.dpToPx(context, someDpInteger);
DimensionHelper.spToPx(context, someSpInteger);
DimensionHelper.ptToPx(context, somePtInteger);
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

### PermissionsHelper
Makes querying app permissions simpler.

To count the number of granted permissions:
```
String[] locationPermissions = new String() {
    Manifest.permission.ACCESS_FINE_LOCATION, 
    Manifest.permission.ACCESS_COARSE_LOCATION};
    
if (PermissionsHelper.countGrantedPermissions(context, locationPermissions) > 0) {
    enableLocationFeatures();
} else {
    requestPermissions();
}
```

To check if all permissions are granted:
```
String[] takeAndSavePhotoPermissions = new String() {
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE};
    
if (PermissionsHelper.checkAllPermissionsGranted(context, takeAndSavePhotoPermissions)) {
    startCamera();
} else {
    requestPermissions();
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

## Views
There is currently one class in the views package: SquareImageView. 

SquareImageView is an ImageView which forces the dimensions to be equal. The XML attribute `derivedDimension` specifies which dimension adjusts to match the other. Currently the derived dimension cannot be changed programatically, but this will probably change in a future release.

Use the view in a layout as follows:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical">
	
	<!-- The width will always equal the height -->
	<com.matthewtamlin.android_utilities.library.views.SquareImageView
		android:layout_width="0dp"
		android:layout_height="wrap_content"
		app:derivedDimension="width"/>

	<!-- The height will always equal the width -->
	<com.matthewtamlin.android_utilities.library.views.SquareImageView
		android:layout_width="wrap_content"
		android:layout_height="0dp"
		app:derivedDimension="height"/>
</LinearLayout>
```

## Utilities
The utilities package contains the UiThreadUtil interface and the LooperUiThreadUtil class. The UiThreadUtil interface provides access to the main thread without depending on the Android frmework, which allows controllers and other logic-containing classes to use concurrently without sacraficing testability. For example, consider the following controller:
```java
public class Controller {
	private final UiThreadUtil threadUtil;
	private final CustomView view;
	
	public Controller(UiThreadUtil threadUtil, CustomView view) {
		this.threadUtil = threadUtil;
		this.view = view;
	}
	
	public void respondToSomeEvent() {
		threadUtil.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				view.doSomething();
			}
		}	
	}
}
```

The controller can be tested against the JVM by passing mocks/stubs to the constructor: 
```java
@Test
public void testRespondToSomeEvent() {
	// Create a UiThreadUtil which executes Runnables on the calling thread
	UiThreadUtil stubThreadUtil = new UiThreadUtil() {
		@Override
		public void runOnUiThread(Runnable runnable) {
			runnable.run();
		}
	}
	
	// Using Mockito framework
	CustomView mockCustomView = mock(CustomView.class);
	
	// Pass stub/mock implementations to controller
	Controller c = new Controller(stubThreadUtil, mockCustomView);
	c.respondToSomeEvent();
	
	// Verify expected behaviour
	verify(mockCustomView, times(1)).doSomething();
}
```

The same controller can be used in production by passing real objects instead:
```java
public class MyActivity extends AppCompatActivity {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		CustomView view = (CustomView) findViewById(R.id.my_custom_view);
		UiThreadUtil threadUtil = LooperThreadUtil.createUsingMainLooper();
	
		Controller c = new Controller(threadUtil, view);
	}
}
```

## Licensing
This library is licensed under the Apache v2.0 licence. Have a look at [the license](LICENSE) for details.

## Dependencies and Attribution
This library uses the following open source libraries as level 1 dependencies:
- [Android Support Library](https://developer.android.com/topic/libraries/support-library/index.html), licensed under the Apache 2.0 license.
- [Java Utilities](https://github.com/MatthewTamlin/JavaUtilities), licensed under the Apache 2.0 license.
- [Timber](https://github.com/JakeWharton/timber), licensed under the Apache 2.0 license.

## Compatibility
This library is compatible with Android 11 and up.
