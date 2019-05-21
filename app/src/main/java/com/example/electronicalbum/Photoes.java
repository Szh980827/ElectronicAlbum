package com.example.electronicalbum;

/**
 * Created by SongZhihao on 2019/5/20.
 */
public class Photoes {
	private String name;
	private int phonoesId;

	public Photoes(String name, int phonoesId) {
		this.name = name;
		this.phonoesId = phonoesId;
	}

	public String getName() {
		return name;
	}

	public int getPhonoesId() {
		return phonoesId;
	}
}
