package com.matthewtamlin.android_utilities_library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.matthewtamlin.android_utilities_library.R;

/**
 * An ImageView where one dimension is forced to equal the other. By default, the height is derived
 * from the width.
 */
public class SquareImageView extends ImageView {
	/**
	 * Used to identify this class during debugging.
	 */
	@SuppressWarnings("unused")
	private static final String TAG = "[SquareImageView]";

	/**
	 * The derived dimension to use if none is supplied.
	 */
	private static final DerivedDimension DEFAULT_DERIVED_DIMENSION = DerivedDimension.HEIGHT;

	/**
	 * The dimension which is forced to equal the other.
	 */
	private DerivedDimension derivedDimension;

	/**
	 * Constructs a new SquareImageViewByWidth instance.
	 *
	 * @param context
	 * 		the context this View is operating in, not null
	 */
	public SquareImageView(final Context context) {
		super(context);
		init(null, 0, 0);
	}

	/**
	 * Constructs a new SquareImageViewByWidth instance.
	 *
	 * @param context
	 * 		the context this SquareImageViewByWidth is operating in, not null
	 * @param attrs
	 * 		configuration attributes, null allowed
	 */
	public SquareImageView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0, 0);
	}

	public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs, defStyleAttr, 0);
	}

	public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr);
		init(attrs, defStyleAttr, defStyleRes);
	}

	/**
	 * Unpacks the attributes, taking into account styling and themes.
	 *
	 * @param attrs
	 * 		configuration attributes, null allowed
	 * @param defStyleAttr
	 * 		an attribute in the current theme which supplies default attributes, pass 0	to ignore
	 * @param defStyleRes
	 * 		a resource which supplies default attributes, only used if {@code defStyleAttr}	is 0, pass
	 * 		0 to ignore
	 */
	private void init(final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
		// Use a TypedArray to process attrs
		final TypedArray attributes = getContext().obtainStyledAttributes(attrs,
				R.styleable.SquareImageView, defStyleAttr, defStyleRes);

		// The derived dimension is encoded as an integer corresponding to an enum ordinal
		final int attrValue = attributes.getInt(R.styleable.SquareImageView_derivedDimension,
				DEFAULT_DERIVED_DIMENSION.ordinal());
		derivedDimension = DerivedDimension.values()[attrValue];

		// Necessary, apparently
		attributes.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		final int dimensions =
				derivedDimension == DerivedDimension.WIDTH ?
						getMeasuredHeight() :
						getMeasuredWidth();

		setMeasuredDimension(dimensions, dimensions);
	}

	/**
	 * Which dimension which is forced to equal the other.
	 */
	private enum DerivedDimension {
		WIDTH,
		HEIGHT
	}
}