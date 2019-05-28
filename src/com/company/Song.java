package com.company;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Song {
    private final String title;
    private final String releaseDate;
    private final String length;
    private final String albumId;

    private static DbConnection connection = DbConnectionFactory.getDbConnection();

    public Song(String title, String releaseDate, String length, String albumId) throws SQLException {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
        this.albumId = albumId;
        create();
    }

    public static void index() throws SQLException {
        String sql = "SELECT * FROM Song";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String title = result.getString(2);
            String releasedDate = result.getString(3);
            String length = result.getString("length");
            String albumId = result.getString("albumId");

            String output = "Song #%d: %s - %s - %s - %s";
            System.out.println(String.format(output, ++count, title, releasedDate, length, albumId));
        }
    }

    private void create() throws SQLException {

        String sql = "INSERT INTO Song(title, releaseDate, length, albumId) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, releaseDate);
        statement.setString(3, length);
        statement.setString(4, albumId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new song was inserted successfully!");
        }
    }

    public static void update(int id, String title, String releaseDate, String length, String albumId) throws SQLException {
        String sql = "UPDATE Song SET title=?, releaseDate=?, length=?, albumId=? WHERE Id=?";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, releaseDate);
        statement.setString(3, length);
        statement.setString(4, albumId);
        statement.setString(5, id + "");

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing song was updated successfully!");
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM Song WHERE id=?";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, id + "");

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A song was deleted successfully!");
        }
    }
}
