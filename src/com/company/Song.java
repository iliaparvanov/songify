package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Song {
    public int id;
    public  String title;
    public  String releaseDate;
    public  String length;
    public  Album album;
    public  Artist artist;

    public Song(int id, String title, String releaseDate, String length, Album album, Artist artist) throws SQLException {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
        this.album = album;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return  title + ", by" + artist.name;




    }
}
