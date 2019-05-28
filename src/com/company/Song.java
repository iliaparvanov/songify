package com.company;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Song {
    private final String title;
    private final String releaseDate;
    private final String lenght;
    private final String albumId;

    private static DbConnection connection = new DbConnection("Songify", "root", "root");

    public Song(String title, String releaseDate, String lenght, String albumId) throws SQLException {
        this.title = title;
        this.releaseDate = releaseDate;
        this.lenght = lenght;
        this.albumId = albumId;
        create();
    }

    private void create() throws SQLException {

        String sql = "INSERT INTO Song(title, releaseDate, lenght, albumId) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.getCon().prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, releaseDate);
        statement.setString(3, lenght);
        statement.setString(4, albumId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new song was inserted successfully!");
        }
    }

    public static void update(int id, String title, String releaseDate, String lenght, String albumId) throws SQLException {
        String sql = "UPDATE Song SET title=?, releaseDate=?, lenght=?, albumId=? WHERE Id=?";

        PreparedStatement statement = connection.getCon().prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, releaseDate);
        statement.setString(3, lenght);
        statement.setString(4, albumId);
        statement.setString(5, id + "");

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing song was updated successfully!");
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM Song WHERE id=?";

        PreparedStatement statement = connection.getCon().prepareStatement(sql);
        statement.setString(1, id + "");

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A song was deleted successfully!");
        }
    }
}
