package com.mercury_wireless.utils.json_2_activity;


import org.pojomatic.annotations.AutoProperty;


import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView.ScaleType;


import com.mercury_wireless.utils.image_view_with_url.ImageViewWithUrl;
import com.mercury_wireless.utils.image_view_with_url.ImageViewWithUrlHandler;


@AutoProperty
final class IamViewImageViewWithUrl extends IamView {
	private final ImageViewWithUrlHandler	imageViewWithUrlHandler;


	private final String					url;


	IamViewImageViewWithUrl(final int backgroundColor, final Point size, final String tag, final String url, final ImageViewWithUrlHandler imageViewWithUrlHandler) {
		super(backgroundColor, size, tag);
		this.url = url;
		this.imageViewWithUrlHandler = imageViewWithUrlHandler;
	}


	@Override
	public final View getView(final Context context, final View convertView) {
		// Cast the view to the proper type
		// This will recycle the view if it already a ImageViewWithUrl
		final ImageViewWithUrl view = ImageViewWithUrl.cast(context, convertView);
		view.setScaleType(ScaleType.FIT_XY);
		applyUiAttributes(view);

		// Set the size:height & size:width
		view.setMaxHeight(size.y);
		view.setMaxWidth(size.x);

		// Set the image url
		view.setImageUrl(url, null, imageViewWithUrlHandler);

		// Return the view to the client
		return view;
	}
}
