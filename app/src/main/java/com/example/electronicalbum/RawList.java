package com.example.electronicalbum;

/**
 * Created by SongZhihao on 2019/5/20.
 */
public class RawList {
	private String name;
	private int iconId;

	public RawList(String name, int iconId) {
		this.name = name;
		this.iconId = iconId;
	}

	public String getName() {
		return name;
	}

	public int getIconId() {
		return iconId;
	}
}
