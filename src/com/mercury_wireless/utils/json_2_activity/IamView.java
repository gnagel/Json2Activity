package com.mercury_wireless.utils.json_2_activity;


import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;


import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.LinearLayout;


@AutoProperty
public abstract class IamView {
	public final int	backgroundColor;


	public final Point	size;


	public final String	tag;


	public IamView(final int backgroundColor, final Point size, final String tag) {
		super();
		this.backgroundColor = backgroundColor;
		this.size = size;
		this.tag = tag;
	}


	public void addViewToParent(final LinearLayout root, final Context context, final View convertView) {
		root.addView(getView(context, convertView), new LinearLayout.LayoutParams(size.x, size.y));
	}


	protected void applyUiAttributes(final View view) {
		// Set the background color from the JSON
		view.setBackgroundColor(backgroundColor);

		// Set the key from the JSON
		view.setTag(tag);

		// Set the size:height
		// We do this instead of LayoutParams since we don't know the parent container's type: LinearLayout, FrameLayout, etc
		view.setMinimumHeight(size.y);

		// Set the size:width
		// We do this instead of LayoutParams since we don't know the parent container's type: LinearLayout, FrameLayout, etc
		view.setMinimumWidth(size.x);
	}


	@Override
	public final boolean equals(final Object other) {
		return Pojomatic.equals(this, other);
	}


	public abstract View getView(Context context, View convertView);


	@Override
	public final int hashCode() {
		return Pojomatic.hashCode(this);
	}


	@Override
	public final String toString() {
		return Pojomatic.toString(this);
	}
}
