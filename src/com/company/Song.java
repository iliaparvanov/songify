package com.company;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Song {
    public final String title;
    public final String releaseDate;
    public final String length;
    public final String albumId;

    private static DbConnection connection = DbConnectionFactory.getDbConnection();

    public Song(String title, String releaseDate, String length, String albumId) throws SQLException {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
        this.albumId = albumId;
    }
}
