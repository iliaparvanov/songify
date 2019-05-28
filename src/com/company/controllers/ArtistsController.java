package com.company.controllers;

import com.company.Artist;
import com.company.DbConnection;
import com.company.DbConnectionFactory;
import com.company.Song;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistsController {

    private final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static void create(Artist artist) throws SQLException {
        String sql = "INSERT INTO Song(Name) VALUES (?)";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, artist.name);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new artist was created successfully!");
        }
    }

    public static void show() {

    }

    public static List<Artist> index() throws SQLException {
        String sql = "SELECT * FROM Artist";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<Artist> artists = new ArrayList<>();
        while (result.next()) {
            artists.add(new Artist(result.getString("Name")));
        }

        return artists;

    }

    public static void update() {

    }

    public static void delete() {

    }
}
