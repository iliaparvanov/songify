package com.company.controllers;

import com.company.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongsController {

    private final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static List<Song> index() throws SQLException {
        String sql = "SELECT * FROM Song";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

//        System.out.println("DEBUG");

        List<Song> songs = new ArrayList<>();
        while (result.next()){
            songs.add(new Song(result.getInt("Id"), result.getString(2), result.getString(3), result.getString("length"),
                    AlbumsController.find(result.getInt("albumId")), ArtistsController.find(result.getInt("artistId"))));
//            String output = "Song #%d: %s - %s - %s - %s";
//            System.out.println(String.format(output, ++count, title, releasedDate, length, albumId));
//            System.out.println("DEBUG");
        }

        return songs;
    }

    public static void create(String title, String releaseDate, String length, Album album, Artist artist) throws SQLException {

        String sql = "INSERT INTO Song(title, releaseDate, length, albumId, artistId) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, releaseDate);
        statement.setString(3, length);
        statement.setInt(4, album.id);
        statement.setInt(5, artist.id);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new song was inserted successfully!");
        }
    }

    public static void update(Song song) throws SQLException {
        String sql = "UPDATE Song SET title=?, releaseDate=?, length=?, albumId=? WHERE Id=?";

        PreparedStatement statement = connection.getConn().prepareStatement(sql);
        statement.setString(1, song.title);
        statement.setString(2, song.releaseDate);
        statement.setString(3, song.length);
        statement.setInt(4, song.album.id);
        statement.setInt(5, song.id);

        System.out.println(song.id);

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

    //TODO: Change this to use an SQL statement

    public static List<Song> find(String name) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Song WHERE title like ?");
        statement.setString(1, "%" + name + "%");

        ResultSet result = statement.executeQuery();
        List<Song> songs = new ArrayList<>();
        while (result.next()) {
            songs.add(new Song(result.getInt("Id"), result.getString("title"), result.getString("releaseDate"), result.getString("length"), AlbumsController.find(result.getInt("albumId")), ArtistsController.find(result.getInt("artistId"))));
        }
        return songs;
    }
}
