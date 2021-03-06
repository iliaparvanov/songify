package com.company.controllers;

import com.company.Album;
import com.company.Artist;
import com.company.DbConnection;
import com.company.DbConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumsController {
    private final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static List<Album> index() throws SQLException {
        String sql = "SELECT * FROM Album";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        List<Album> albums = new ArrayList<>();

        while (result.next()){
            String title = result.getString("title");
            int artistId = result.getInt("artistId");

            albums.add(new Album(result.getInt("Id"), title, ArtistsController.find(artistId)));
        }
        return albums;
    }

    public static Album create(String title, Artist artist) throws SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("INSERT INTO Album (title, artistId) VALUES(?, ?)");
        statement.setString(1, title);
        statement.setInt(2, artist.id);

        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0){
            System.out.println("Album inserted succesfully");
        }

        return findFirst(title, artist);
    }

    public static void delete(int id) throws SQLException {
        SongsController.delete(AlbumsController.find(id));
        PreparedStatement statement = connection.getConn().prepareStatement("DELETE FROM Album WHERE id = ?");
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("Album deleted succesfully");
        }
    }

    public static void delete(Artist artist) throws SQLException {
        AlbumsController.find(artist).stream().forEach((a) -> {
            try {
                SongsController.delete(a);
            } catch(SQLException e) {
                e.printStackTrace();
            }});
        PreparedStatement statement = connection.getConn().prepareStatement("DELETE FROM Album WHERE ArtistID= ?");
        statement.setString(1, String.valueOf(artist.id));
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("Album(s) deleted succesfully");
        }
    }

    public static void update(Album album) throws SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("UPDATE Album SET title=?, artistId=? WHERE Id=?");

        statement.setString(1, album.getTitle());
        statement.setInt(2, album.getArtist().id);
        statement.setInt(3, album.id);

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Album updated succesfully");
        }
    }




    public static List<Album> find(Artist artist) throws SQLException{
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Album WHERE ArtistID = ?");
        statement.setInt(1, artist.id);

        ResultSet result = statement.executeQuery();
        List<Album> albums = new ArrayList<>();
        while (result.next()) {
            albums.add(new Album(result.getInt("Id"), result.getString("Title"), ArtistsController.find(result.getInt("ArtistID"))));
        }
        return albums;
    }

    public static List<Album> find(String title) throws SQLException{
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Album WHERE Title LIKE ?");
        statement.setString(1, "%" + title  + "%");

        ResultSet result = statement.executeQuery();
        List<Album> albums = new ArrayList<>();
        while (result.next()) {
            albums.add(new Album(result.getInt("Id"), result.getString("Title"), ArtistsController.find(result.getInt("ArtistID"))));
        }
        return albums;
    }

    public static Album findFirst(String title, Artist artist) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Album WHERE Title = ? AND ArtistID = ?");
        statement.setString(1, title);
        statement.setInt(2, artist.id);

        ResultSet result = statement.executeQuery();
        List<Album> albums = new ArrayList<>();
        while (result.next()) {
            albums.add(new Album(result.getInt("Id"), result.getString("Title"), ArtistsController.find(result.getInt("ArtistID"))));
        }
        return albums.get(0);
    }

    public static Album find(int id) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Album WHERE Id = ?");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        List<Album> albums = new ArrayList<>();
        while (result.next()) {
            albums.add(new Album(id, result.getString("Title"), ArtistsController.find(result.getInt("ArtistID"))));
        }
        return albums.get(0);
    }
}
