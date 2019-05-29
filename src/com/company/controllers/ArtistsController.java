package com.company.controllers;

import com.company.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistsController {

    private final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static void create(String name) throws SQLException {
        String sql = "INSERT INTO Artist(Name) VALUES (?)";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, name);

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
            artists.add(new Artist(result.getInt("Id"), result.getString("Name")));
        }

        return artists;
    }

    public static void update(Artist artist) throws SQLException {
        PreparedStatement statement = connection.getConn()
                .prepareStatement("UPDATE Artist SET name=? WHERE Id=?");

        statement.setString(1, artist.name);
        statement.setInt(2, artist.id);

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Artist updated succesfully");
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM Artist WHERE id=?";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, id + "");

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("An artist was deleted successfully!");
        }
    }

    public static List<Artist> find(String name) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Artist WHERE Name like ?");
        statement.setString(1, "%" + name + "%");

        ResultSet result = statement.executeQuery();
        List<Artist> artists = new ArrayList<>();
        while (result.next()) {
            artists.add(new Artist(result.getInt("Id"), result.getString("Name")));
        }
        return artists;
    }

    public static Artist find(int id) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Artist WHERE Id LIKE ?");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        Artist artist = null;
        while (result.next()) {
            artist = new Artist(id, result.getString("Name"));
        }
        return artist;
    }
}
