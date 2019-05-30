package com.company.controllers;

import com.company.DbConnection;
import com.company.DbConnectionFactory;
import com.company.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenresController {

    private final static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static List<Genre> index() throws SQLException {
        String sql = "SELECT * FROM Genre";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        List<Genre> genres = new ArrayList<>();

        while (result.next()){
            genres.add(new Genre(result.getInt(1), result.getString("name")));
        }
        return genres;
    }

    public static void create(String name) throws SQLException{

        PreparedStatement statement = connection.getConn()
                .prepareStatement("INSERT INTO Genre (name) VALUES(?)");

        statement.setString(1, name);

        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0){
            System.out.println("Genreinserted succesfully");
        }
    }

    public static void delete(int id) throws SQLException{

        PreparedStatement statement = connection.getConn().prepareStatement("DELETE FROM Genre WHERE id = ?");
        statement.setString(1, id+"");
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("Genre deleted succesfully");
        }

    }

    public static void update(Genre genre) throws  SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("UPDATE Genre SET name=? WHERE Id=?");

        statement.setString(1, genre.getName());
        statement.setInt(2, genre.id);

        System.out.println("Genre name: " + genre.getName() + "; genre id: " + genre.id);

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Genre updated succesfully");
        }  else {
            System.out.println("Could not update Genre");
        }
    }

    public static void show(String name) throws  SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("SELECT * From Genre WHERE title="+ name);
        statement.executeQuery();
    }

    public static Genre find(String name) throws SQLException {
        PreparedStatement statement = connection.getConn().prepareStatement("SELECT * FROM Genre WHERE name like ?");
        statement.setString(1, "%" + name + "%");

        ResultSet result = statement.executeQuery();
        if(!result.next()) {
            return null;
        }
        Genre genre = new Genre(result.getInt("Id"), result.getString("name"));

        return genre;
    }
}
