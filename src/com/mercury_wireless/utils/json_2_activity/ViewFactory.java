package com.mercury_wireless.utils.json_2_activity;


import java.util.ArrayList;


import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.Point;


import com.mercury_wireless.utils.image_view_with_url.ImageViewWithUrlHandler;


public class ViewFactory extends ContextWrapper {

	private final ImageViewWithUrlHandler	imageViewWithUrlHandler;


	public ViewFactory(final Context context, final ImageViewWithUrlHandler imageViewWithUrlHandler) {
		super(context);
		this.imageViewWithUrlHandler = imageViewWithUrlHandler;
	}


	private int _backgroundColor(final JSONObject json) throws JSONException {
		final String value = json.optString("backgroundColor", "#000000");
		return Color.parseColor(value);
	}


	private Point _size(final JSONObject json) throws JSONException {
		final JSONObject size = json.getJSONObject("size");
		final int width = size.getInt("width");
		final int height = size.getInt("height");
		return new Point(width, height);
	}


	public IamView parse(final JSONObject json) throws JSONException {
		if (null == json) {
			throw new IllegalArgumentException("Null json");
		}

		final String type = json.getString("type");
		if (StringUtils.equals(type, "ImageViewWithUrl")) {
			return parseImageViewWithUrl(json);
		}

		if (StringUtils.equals(type, "ListView")) {
			return parseListView(json);
		}

		throw new IllegalStateException("Unknown type=" + type + ", json=" + json);
	}


	// "backgroundColor": "#FF000000",
	// "key": "ScrollView0",
	// "size": {"width": 1024, "height": 768},
	// "type": "ImageViewWithUrl",
	// "url": "http://animalsandpetsplants.com/wp-content/uploads/2013/01/Beautifull-cat-cats-14749885-1600-1200.jpg"
	private IamView parseImageViewWithUrl(final JSONObject json) throws JSONException {
		final int backgroundColor = _backgroundColor(json);
		final String tag = json.getString("tag");
		final String url = json.getString("url");
		final Point size = _size(json);

		return new IamViewImageViewWithUrl(backgroundColor, size, tag, url, imageViewWithUrlHandler);
	}


	// "size": {"width": 1024, "height": 768},
	// "key": "Chapter1ScrollView",
	// "paging": true,
	// "stopHidesNav": true,
	// "type": "ListView",
	// "subviews": { "subviewArray": [...] }
	private IamView parseListView(final JSONObject json) throws JSONException {
		final int backgroundColor = _backgroundColor(json);
		final String tag = json.getString("tag");
		final Point size = _size(json);

		// Get the entries of the array
		final ArrayList<IamView> values = new ArrayList<IamView>();
		final JSONArray array = json.getJSONObject("subviews").getJSONArray("subviewArray");
		for (int i = 0, max = array.length(); i < max; i++) {
			values.add(parse(array.getJSONObject(i)));
		}

		return new IamViewListView(backgroundColor, size, tag, values);
	}

}
