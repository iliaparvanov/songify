package com.company;

public class Album {
    public final int id;
    public final String title;
    public final Artist artist;

    public Album(int id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }
}
