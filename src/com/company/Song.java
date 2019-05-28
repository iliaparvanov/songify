package com.company;

import com.mysql.cj.x.protobuf.MysqlxCrud;

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

    public Song(String title, String releaseDate, String length, Album album, Artist artist) throws SQLException {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
        this.album = album;
        this.artist = artist;
    }
}
