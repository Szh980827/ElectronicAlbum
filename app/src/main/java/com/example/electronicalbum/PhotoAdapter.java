package com.example.electronicalbum;

import android.content.Context;
import android.content.Intent;
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
 * Created by SongZhihao on 2019/5/23.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

	private Context mContext;
	private List<Photo> mPhotoeList;

	static class ViewHolder extends RecyclerView.ViewHolder {

		CardView cardView;
		ImageView photoImage;

		public ViewHolder(@NonNull View view) {
			super(view);
			cardView = (CardView) view;
			photoImage = view.findViewById(R.id.photo_ima);
		}
	}

	public PhotoAdapter(List<Photo> photoList) {
		mPhotoeList = photoList;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
		if (mContext == null) {
			mContext = parent.getContext();
		}
		View view = LayoutInflater.from(mContext).inflate(R.layout.photo_item, parent, false);
		final ViewHolder holder = new ViewHolder(view);
		holder.cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = holder.getAdapterPosition();
				Photo photo = mPhotoeList.get(position);
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
		Photo photo = mPhotoeList.get(position);

		Glide.with(mContext).load(photo.getPhotoId()).asBitmap().into(new TransformationUtils(viewHolder.photoImage));
	}

	@Override
	public int getItemCount() {
		return mPhotoeList.size();
	}


}