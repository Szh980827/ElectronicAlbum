package com.example.electronicalbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SongZhihao on 2019/5/20.
 */

public class RawlistAdapter extends ArrayAdapter<RawList> {

	private int resourceId;

	public RawlistAdapter(Context context, int textViewResourceId, List<RawList> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RawList rawList = getItem(position);

		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
					false);
			viewHolder = new ViewHolder();
			viewHolder.rawImage = view.findViewById(R.id.raw_image);
			viewHolder.rawName = view.findViewById(R.id.raw_name);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.rawImage.setImageResource(rawList.getIconId());
		viewHolder.rawName.setText(rawList.getName());
		return view;
	}

	class ViewHolder {
		ImageView rawImage;
		TextView rawName;
	}
}
