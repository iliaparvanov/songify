package com.company.controllers;

import com.company.DbConnection;
import com.company.DbConnectionFactory;
import com.company.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenresController {

    static DbConnection connection = DbConnectionFactory.getDbConnection();

    public static void index() throws SQLException {
        String sql = "SELECT * FROM Song";

        Statement statement = connection.getConn().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String name = result.getString("name");

            String output = "Genre #%d: %s";
            System.out.println(String.format(output, ++count, name));
        }
    }

    public static void create(Genre genre) throws SQLException{

        PreparedStatement statement = connection.getConn()
                .prepareStatement("INSERT INTO Genre (name) VALUES("+ genre.name +")");



        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0){
            System.out.println("Album inserted succesfully");
        }
    }

    public void delete(int id) throws SQLException{

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

        statement.setString(1, genre.name);
        statement.setInt(3, genre.id);

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Genre updated succesfully");
        }
    }

    public static void show(String name) throws  SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("SELECT * From Genre WHERE title="+ name);
        statement.executeQuery();
    }

    public static Genre find(int id) throws SQLException {
        PreparedStatement statement = connection.getConn()
                .prepareStatement("SELECT * From Genre WHERE id="+ id);
        ResultSet result = statement.executeQuery();
        String name = result.getString("name");

        return new Genre(name);
    }
}
