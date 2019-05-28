package com.company.controllers;

import com.company.Album;
import com.company.DbConnection;
import com.company.DbConnectionFactory;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

            albums.add(new Album(title, artistId));
        }
        return albums;
    }

    public static void create(Album album) throws SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("INSERT INTO Album (title, artistId) VALUES(?, ?)");
        statement.setString(1, album.title);
        statement.setString(2, album.artistId+"");

        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0){
            System.out.println("Genre inserted succesfully");
        }

    }

    public void delete(int id) throws SQLException {

        PreparedStatement statement = connection.getConn().prepareStatement("DELETE FROM Album WHERE id = ?");
        statement.setString(1, id+"");
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("Genre deleted succesfully");
        }
    }

    public static void update(Album album) throws SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("UPDATE Album SET title=?, artistId=? WHERE Id=?");

        statement.setString(1, album.title);
        statement.setString(2, album.artistId+"");
        statement.setInt(3, album.id);

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Genre updated succesfully");
        }
    }

    public static void show(String title) throws SQLException{

        PreparedStatement statement = connection.getConn()
                .prepareStatement("SELECT * From Album WHERE title="+ title);
        statement.executeQuery();
    }

    public static List<Album> find(String title) throws SQLException{
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Album WHERE Title = ?");
        statement.setString(1, title);

        ResultSet result = statement.executeQuery();
        List<Album> albums = new ArrayList<>();
        while (result.next()) {
            albums.add(new Album(result.getString("Title"), result.getInt("ArtistID")));
        }
        return albums;
    }

    public static Album find(int id) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Album WHERE Id = ?");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        List<Album> albums = new ArrayList<>();
        while (result.next()) {
            albums.add(new Album(result.getString("Title"), result.getInt("ArtistID")));
        }
        return albums.get(0);
    }
}
