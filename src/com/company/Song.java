package com.company;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Song {
    public int id;
    public final String title;
    public final String releaseDate;
    public final String length;
    public final Album album;

    public Song(String title, String releaseDate, String length, Album album) throws SQLException {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
        this.album = album;
    }
}
