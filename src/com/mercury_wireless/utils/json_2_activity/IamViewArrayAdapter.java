package com.mercury_wireless.utils.json_2_activity;


import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;




final class IamViewArrayAdapter extends android.widget.ArrayAdapter<IamView> {
	IamViewArrayAdapter(final Context context, final List<IamView> values) {
		super(context, 0, values);
	}


	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		return getItem(position).getView(getContext(), convertView);
	}
}
