package com.example.electronicalbum;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by SongZhihao on 2019/5/21.
 */
public class PhotoesAdapter extends RecyclerView.Adapter<PhotoesAdapter.ViewHolder> {

	private Context mContext;
	private List<Photoes> mPhotoesList;

	static class ViewHolder extends RecyclerView.ViewHolder {

		CardView cardView;
		ImageView photoImage;
		TextView photoName;

		public ViewHolder(@NonNull View view) {
			super(view);
			cardView = (CardView) view;
			photoImage = view.findViewById(R.id.photo_image);
			photoName = view.findViewById(R.id.photo_name);
		}
	}

	public PhotoesAdapter(List<Photoes> photoesList) {
		mPhotoesList = photoesList;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
		if (mContext == null) {
			mContext = parent.getContext();
		}
		View view = LayoutInflater.from(mContext).inflate(R.layout.phonoes_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
		Photoes photoes = mPhotoesList.get(position);
		viewHolder.photoName.setText(photoes.getName());
		Glide.with(mContext).load(photoes.getPhonoesId()).into(viewHolder.photoImage);
	}

	@Override
	public int getItemCount() {
		return mPhotoesList.size();
	}
}
