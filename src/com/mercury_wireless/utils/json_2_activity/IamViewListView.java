package com.mercury_wireless.utils.json_2_activity;


import java.util.List;


import org.pojomatic.annotations.AutoProperty;


import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.ListView;


@AutoProperty
final class IamViewListView extends IamView {
	private final List<IamView>	values;


	public IamViewListView(final int backgroundColor, final Point size, final String tag, final List<IamView> values) {
		super(backgroundColor, size, tag);
		this.values = values;
	}


	@Override
	public View getView(final Context context, final View convertView) {
		final ListView view = null != convertView && convertView instanceof ListView ? (ListView) convertView : new ListView(context);
		applyUiAttributes(view);

		// Set adapter
		view.setAdapter(new IamViewArrayAdapter(context, values));

		return view;
	}
}
