package com.company;

import com.company.DbConnection;

import java.sql.SQLException;

public class Album {
    private final String title;
    private final int artistId;

    DbConnection connection = new DbConnection("Songify", "xothas", "asdf");

    public Album(String title, int artistId) {
        this.title = title;
        this.artistId = artistId;
    }

    private void create(){
        try {
            connection.insertStatment("INSERT INTO Album (title, artistId) VALUES("+ this.title + ", "+this.artistId+ ");" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void delete(){
        try {
            connection.insertStatment("DELETE FROM Album WHERE title = "+ this.title +";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void update(){

    }
    private void show(){
        try {
            connection.insertStatment("SELECT * FROM Genre Where title="+ this.title+ ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
