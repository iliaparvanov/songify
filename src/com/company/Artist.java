package com.company;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Artist {
    private String name;

    DbConnection connection = DbConnectionFactory.getIliaDbConnection();

    public Artist(String name) {
        this.name = name;
    }

    public void create() throws SQLException {
        String sql = "INSERT INTO Song(Name) VALUES (?)";

        PreparedStatement statement = connection.getCon().prepareStatement(sql);
        statement.setString(1, name);

        int rowsInserted = connection.update(statement);
        if (rowsInserted > 0) {
            System.out.println("A new artist was created successfully!");
        }
    }

    public void show() {

    }

    public void index() {

    }

    public void update() {

    }

    public void delete() {

    }
}
