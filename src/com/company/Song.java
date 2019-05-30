package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Song {
    public int id;
    public  String title;
    public  String releaseDate;
    public  String length;
    public  Album album;
    public  List<Artist> artists;

    public Song(int id, String title, String releaseDate, String length, Album album, List<Artist> artists) throws SQLException {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
        this.album = album;
        this.artists = artists;
    }

    @Override
    public String toString() {
        String aStrings = "";
        for (Artist a : artists) {
            aStrings = aStrings + artists.toString() + " ";
        }
        return  title + ", by " + aStrings;

    }
}
