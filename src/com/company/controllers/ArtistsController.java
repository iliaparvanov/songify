package com.company.controllers;

import com.company.Artist;
import com.company.DbConnection;
import com.company.DbConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public static void index() {

    }

    public static void update() {

    }

    public static void delete() {

    }
}
