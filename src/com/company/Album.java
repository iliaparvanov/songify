package com.company;

public class Album {
    public final int id;
    public String title;
    public Artist artist;

    public Album(int id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return  title + ", by " + artist.name;
    }
}
