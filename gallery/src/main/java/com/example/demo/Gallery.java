package com.example.demo;

public class Gallery {
	private int id;
	private Object images;

	public Gallery() {

	}

	public Gallery(int id, Object images) {
		super();
		this.id = id;
		this.images = images;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Object getImages() {
		return images;
	}
	public void setImages(Object images) {
		this.images = images;
	}
	public int getId() {
		return id;
	}	


}
