package com.company.controllers;

import com.company.DbConnection;
import com.company.DbConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArtistsController {

    private final static DbConnection connection = DbConnectionFactory.getIliaDbConnection();

    public static void create() throws SQLException {
        String sql = "INSERT INTO Song(Name) VALUES (?)";

        PreparedStatement statement = connection.getCon().prepareStatement(sql);
        statement.setString(1, name);

        int rowsInserted = connection.update(statement);
        if (rowsInserted > 0) {
            System.out.println("A new artist was created successfully!");
        }
    }
}
