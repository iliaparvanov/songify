package com.company.controllers;

import com.company.Album;
import com.company.DbConnection;
import com.company.DbConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlbumsController {
    private final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static void index() throws SQLException {
        String sql = "SELECT * FROM Song";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String title = result.getString("title");
            String artistId = result.getString("artistId");

            String output = "Song #%d: %s - %s";
            System.out.println(String.format(output, ++count, title, artistId));
        }
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

    public static Album find(int id) throws SQLException{

        PreparedStatement statement = connection.getConn()
                .prepareStatement("SELECT * From Album WHERE id="+ id);
        ResultSet result = statement.executeQuery();
        Album album = new Album(result.getString("title"), result.getInt("artistId"));
        return album;
    }
}
