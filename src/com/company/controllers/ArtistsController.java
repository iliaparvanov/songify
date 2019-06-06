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

    public static Artist create(String name) throws SQLException {
        String sql = "INSERT INTO Artist(Name) VALUES (?)";

        PreparedStatement statement = connection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        int insertedRows = statement.executeUpdate();
        if (insertedRows > 0) {
            System.out.println("A new artist was created successfully!");
        }

        int id = 0;
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return new Artist(id, name);
    }





    public static List<Artist> index() throws SQLException {
        String sql = "SELECT * FROM Artist";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<Artist> artists = new ArrayList<>();
        while (result.next()) {
            artists.add(new Artist(result.getInt("Id"), result.getString("Name")));
//            System.out.println("Name of result: " + result.getString("Name"));
        }

        return artists;
    }

    public static void update(Artist artist) throws SQLException {
        PreparedStatement statement = connection.getConn()
                .prepareStatement("UPDATE Artist SET name=? WHERE Id=?");

        statement.setString(1, artist.getName());
        statement.setInt(2, artist.id);

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Artist updated succesfully");
        }


    }

    public static void delete(int id) throws SQLException {
        connection.getConn().setAutoCommit(false);

        try {
            SongsController.delete(ArtistsController.find(id));
            AlbumsController.delete(ArtistsController.find(id));
            String sql = "DELETE FROM Artist WHERE Id=?";

            PreparedStatement statement = connection.getConn().prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
            statement.execute();
            statement = connection.getConn().prepareStatement(sql);
            statement.setInt(1, id );

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An artist was deleted successfully!");
            }

            statement = connection.getConn().prepareStatement("DELETE FROM ArtistSong WHERE ArtistId = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch(Exception e) {
            connection.getConn().rollback();
            e.printStackTrace();
        } finally {
            connection.getConn().setAutoCommit(true);

        }

    }

    public static List<Artist> find(String name) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Artist WHERE Name LIKE ?");
        statement.setString(1, "%" + name + "%");

        ResultSet result = statement.executeQuery();
        List<Artist> artists = new ArrayList<>();
        while (result.next()) {
            artists.add(new Artist(result.getInt("Id"), result.getString("Name")));
        }
        return artists;
    }

    public static Artist find(int id) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Artist WHERE Id = ?");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        Artist artist = null;
        while (result.next()) {
            artist = new Artist(id, result.getString("Name"));
        }
        return artist;
    }

    public static List<Artist> findBySongId(int songId) throws SQLException {
        List<Artist> artists = new ArrayList<>();
        PreparedStatement preparedStatement = connection.getConn().prepareStatement("SELECT * FROM ArtistSong WHERE SongId = ?");
        preparedStatement.setInt(1, songId);
        ResultSet joinTableResult = preparedStatement.executeQuery();
        while (joinTableResult.next()) {
            artists.add(ArtistsController.find(joinTableResult.getInt("ArtistId")));
        }
        return artists;
    }
}
