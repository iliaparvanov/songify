package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Genre {

    private final String name;
    static DbConnection connection = new DbConnection("Songify", "xothas", "asdf");

    public Genre(String name) {
        this.name = name;
    }

    public static void index() throws SQLException {
        String sql = "SELECT * FROM Song";

        Statement statement = connection.getCon().createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String name = result.getString("name");

            String output = "Genre #%d: %s";
            System.out.println(String.format(output, ++count, name));
        }
    }

    private void create() throws SQLException{

        PreparedStatement statement = connection.getCon()
                .prepareStatement("INSERT INTO Genre (name) VALUES("+ this.name+")");



        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0){
            System.out.println("Album inserted succesfully");
        }
    }
    private void delete(int id) throws SQLException{

        PreparedStatement statement = connection.getCon().prepareStatement("DELETE FROM Genre WHERE id = ?");
        statement.setString(1, id+"");
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("Genre deleted succesfully");
        }

    }
    private void update(int id, String name) throws  SQLException{
        PreparedStatement statement = connection.getCon()
                .prepareStatement("UPDATE Genre SET name=? WHERE Id=?");

        statement.setString(1, name);
        statement.setString(3, id+"");

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Genre updated succesfully");
        }
    }
    private void show(String name) throws  SQLException{
        PreparedStatement statement = connection.getCon()
                .prepareStatement("SELECT * From Genre WHERE title="+ name);
        statement.executeQuery();
    }
}
