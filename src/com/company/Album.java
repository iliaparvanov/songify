package com.company;

import com.company.DbConnection;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Album {
    private final String title;
    private final int artistId;

    static DbConnection connection = DbConnectionFactory.getDbConnection();

    public Album(String title, int artistId) {
        this.title = title;
        this.artistId = artistId;
    }

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

    private void create() throws SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("INSERT INTO Album (title, artistId) VALUES(?, ?)");
        statement.setString(1, this.title);
        statement.setString(2, this.artistId+"");

        int rowsInserted = statement.executeUpdate();
        if(rowsInserted > 0){
            System.out.println("Genre inserted succesfully");
        }

    }
    private void delete(int id) throws SQLException {

        PreparedStatement statement = connection.getConn().prepareStatement("DELETE FROM Album WHERE id = ?");
        statement.setString(1, id+"");
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("Genre deleted succesfully");
        }
    }
    private void update(int id, String title, int artistId) throws SQLException{
        PreparedStatement statement = connection.getConn()
                .prepareStatement("UPDATE Album SET title=?, artistId=? WHERE Id=?");

        statement.setString(1, title);
        statement.setString(2, artistId+"");
        statement.setString(3, id+"");

        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0){
            System.out.println("Genre updated succesfully");
        }
    }
    private void show(String title) throws SQLException{

        PreparedStatement statement = connection.getConn()
                .prepareStatement("SELECT * From Album WHERE title="+ title);
        statement.executeQuery();


    }
}
