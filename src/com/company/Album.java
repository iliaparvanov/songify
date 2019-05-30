package com.company;

import javafx.beans.property.SimpleStringProperty;

public class Album {
    public final int id;

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    private SimpleStringProperty title;
    private Artist artist;

    public Album(int id, String title, Artist artist) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.artist = artist;
    }

    @Override
    public String toString() {
        return  getTitle() + ", by " + artist.getName();
    }
}
